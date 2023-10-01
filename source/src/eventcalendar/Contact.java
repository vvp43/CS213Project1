package eventcalendar;

/**
 * This contact class is
 * used to retrieve Department and Email info
 *
 * @author Seth Yeh
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Default constructor
     *
     * @param department department info
     * @param email      email of department
     */
    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    /**
     * Getter method for Department
     *
     * @return department enum
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Getter method for email
     *
     * @return email string
     */
    public String getEmail() {
        return email;
    }


    /**
     * isValid() method
     * checks for whether given email is in rutgers domain and from one of 5 departments
     *
     * @return true if contact info is valid
     */
    public boolean isValid() {
        if (email.contains("@rutgers.edu")) {
            String sub = email.substring(0, email.indexOf("@rutgers.edu"));
            if (sub.equalsIgnoreCase(String.valueOf(Department.CS)) ||
                    sub.equalsIgnoreCase(String.valueOf(Department.EE))
                    || sub.equalsIgnoreCase(String.valueOf(Department.ITI)) ||
                    sub.equalsIgnoreCase(String.valueOf(Department.MATH))
                    || sub.equalsIgnoreCase(String.valueOf(Department.BAIT))) {

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}