package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    /**
     * Constructs a new Remark instance.
     *
     * @param remark The string representation of Remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Overrides toString method.
     * Returns the String representations of the Remark.
     *
     * @return the String representations of the Remark.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides equals method.
     *
     * @param other the Object to be compared with.
     * @return True if both Objects are equal.
     */

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    /**
     * Overrides hasCode method from parent class.
     *
     * @return the value hashCode.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
