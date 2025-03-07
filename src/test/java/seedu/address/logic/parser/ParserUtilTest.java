package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_FINANCIAL_PLAN = "Plan !!";
    private static final String VALID_NAME_1 = "Rachel Walker";
    private static final String VALID_NAME_2 = "Captain Kek";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_FINANCIAL_PLAN_1 = "Plan A";
    private static final String VALID_FINANCIAL_PLAN_2 = "Plan B";
    private static final String VALID_APPOINTMENT_DESC = "Review Insurance";
    private static final String VALID_APPOINTMENT_DATE = "01-01-2023 20:00";
    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_APPOINTMENT_DESC = "#Review Insurance*";
    private static final String INVALID_APPOINTMENT_DATE_FORMAT = "1 May 2023 20:00";
    private static final String INVALID_APPOINTMENT_DATE = "01-13-2023 20:00";
    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME_1);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME_1));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME_1 + WHITESPACE;
        Name expectedName = new Name(VALID_NAME_1);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseAppointment_validValueAndDate_returnsAppointment() throws Exception {
        Appointment expectedAppointment = new Appointment("Review Insurance",
                LocalDateTime.of(2023, 1, 1, 20, 0));
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC, VALID_APPOINTMENT_DATE));
    }

    @Test
    public void parseAppointment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointment(null,
                null));
    }

    @Test
    public void parseAppointment_invalidInputs_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(INVALID_APPOINTMENT_DESC,
                VALID_APPOINTMENT_DATE));

        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC,
                INVALID_APPOINTMENT_DATE_FORMAT));

        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC,
                INVALID_APPOINTMENT_DATE));
    }

    @Test
    public void validateName_validInput_success() {
        try {
            ParserUtil.validateName(VALID_NAME_1);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    public void validateName_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.validateName(INVALID_NAME));
    }
    @Test
    public void validateTag_validInput_success() {
        try {
            ParserUtil.validateTag(VALID_TAG_1);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    public void validateTag_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.validateTag(INVALID_TAG));

        // with white space
        assertThrows(ParseException.class, () -> ParserUtil.validateTag(VALID_TAG_1 + " " + VALID_TAG_2));
    }
    @Test
    public void validateFinancialPlan_validInput_success() {
        try {
            ParserUtil.validateFinancialPlan(VALID_FINANCIAL_PLAN_1);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    public void validateFinancialPlan_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.validateFinancialPlan(INVALID_FINANCIAL_PLAN));
    }
    @Test
    void isValidDay_validDate_shouldReturnTrue() {
        // November has 30 days
        YearMonth yearMonth = YearMonth.of(2022, 11);
        String date = "15-11-2022";
        assertTrue(ParserUtil.isValidDay(yearMonth, date));
    }
    @Test
    void isValidDay_invalidDate_shouldReturnFalse() {
        // February has 28 days
        YearMonth yearMonth = YearMonth.of(2022, 2);
        String date = "31-02-2022";
        assertFalse(ParserUtil.isValidDay(yearMonth, date));
    }

    @Test
    void parseDate_validDate_shouldParseSuccessfully() throws ParseException {
        String validDateString = "29-02-2024";
        try {
            ParserUtil.parseDate(validDateString);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void parseDate_invalidDate_shouldThrowParseException() {
        // 29th Feb for non leap year
        String invalidDateString1 = "29-02-2022";
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(invalidDateString1));

        // No 31st Nov
        String invalidDateString2 = "31-04-2023";
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(invalidDateString2));
    }
    @Test
    void parseAppointment_validInput_shouldParseSuccessfully() throws ParseException {
        try {
            ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC, VALID_APPOINTMENT_DATE);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void parseAppointment_invalidInput_shouldThrowParseException() {
        // Invalid description
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointment(INVALID_APPOINTMENT_DESC, VALID_APPOINTMENT_DATE));
        // 29th Feb for non leap year
        String invalidDateString1 = "29-02-2022 14:30";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC, invalidDateString1));
        // No 31st Nov
        String invalidDateString2 = "31-11-2023 14:30";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC, invalidDateString2));

        // Wrong format
        String invalidFormat = "1";
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointment(VALID_APPOINTMENT_DESC, invalidFormat));
    }
    @Test
    void parseAppointmentDate_validDate_shouldParseSuccessfully() throws ParseException {
        String validDateTimeString = "29-02-2024 14:30";
        try {
            ParserUtil.parseAppointmentDate(validDateTimeString);
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void parseAppointmentDate_invalidDate_shouldThrowParseException() {
        // Wrong format
        String invalidDateTime = "1";
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDate(invalidDateTime));

        // 29th Feb for non leap year
        String invalidDateTime1 = "29-02-2022 14:30";
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDate(invalidDateTime1));

        // No 31st Nov
        String invalidDateTime2 = "31-11-2023 14:30";
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDate(invalidDateTime2));
    }
}
