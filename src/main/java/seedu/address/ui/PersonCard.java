package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label nextOfKinName;
    @FXML
    private Label nextOfKinPhone;
    @FXML
    private Label appointment;
    @FXML
    private FlowPane financialPlans;
    @FXML
    private Label tagsTitle;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        String nextOfKinNameText = "Next-of-kin: " + person.getNextOfKinName().fullName;
        nextOfKinName.setText(nextOfKinNameText);
        String nextOfKinPhoneText = "Next-of-kin Phone: " + person.getNextOfKinPhone().value;
        nextOfKinPhone.setText(nextOfKinPhoneText);
        String appointmentText = "Appointment: " + person.getAppointment().toString();
        appointment.setText(appointmentText);
        person.getFinancialPlans().stream()
                .sorted(Comparator.comparing(financialPlan -> financialPlan.financialPlanName))
                .forEach(financialPlan -> financialPlans.getChildren()
                        .add(new Label(financialPlan.financialPlanName)));
        financialPlans.getChildren().forEach(label -> ((Label) label).setMaxWidth(500));
        financialPlans.getChildren().forEach(label -> ((Label) label).setWrapText(true));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getChildren().forEach(label -> ((Label) label).setMaxWidth(500));
        tags.getChildren().forEach(label -> ((Label) label).setWrapText(true));
    }
}
