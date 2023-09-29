package eventcalendar;

/**
 * This contact class is
 * used to retrieve Department and Email info
 * @author Seth Yeh
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Constructor
     * @param department
     * @param email
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     * Getter method for Department
     * @return
     */
    public Department getDepartment(){
        return department;
    }

    /**
     * Getter method for email
     * @return
     */
    public String getEmail(){
        return email;
    }


    /**
     * isValid() method
     *idk if were suppose to check for case sensitivity for department here because contact literally
     *comes with department as one if its variables
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
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}