package eventcalendar;

/**
 * This enum class lists departments using their acronyms as constant names.
 * The full names of the departments are properties
 * @author Vinh Pham
 */
public enum Department {
    CS("computer science"),
    EE("electrical engineering"),
    ITI("information technology and informatics"),
    MATH("mathemasdisauyhasauisatics"),
    BAIT("business analytics and information technology");

    final String fullName;

    /**
     * Property: display full name of department
     * @param fullName: full name of the department
     */
    Department(String fullName) {
        this.fullName = fullName;
    }
}
