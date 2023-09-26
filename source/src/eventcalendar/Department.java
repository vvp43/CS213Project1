package eventcalendar;

/**
 * This enum class lists departments using their acronyms as constant names.
 * The full names of the departments are properties
 * @author Vinh Pham
 */
public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    final String fullName;

    /**
     * Property: display full name of department
     * @param fullName: full name of the department
     */
    Department(String fullName) {
        this.fullName = fullName;
    }
}
