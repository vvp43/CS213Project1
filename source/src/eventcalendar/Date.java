package eventcalendar;
import java.util.Calendar;

/**
 * This Date class retrieves information
 * about date which contains year, month and day
 * @author Seth Yeh, Vinh Pham
 */
public class Date implements Comparable<Date> { // <--- no idea what this does
    private int year;
    private int month;
    private int day;
    final Calendar curr = Calendar.getInstance(); // the current year date
    Calendar event = Calendar.getInstance(); // the event date

    /**
     * Constructor with param year, month and day
     *
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        event.set(year, month, day);
    }

    /**
     * add() method
     *
     * @param minutes
     * @return
     */
    public String add(int minutes) {
        Calendar temp = Calendar.getInstance();
        temp = event;
        temp.add(Calendar.MINUTE, minutes);

        int hour = temp.get(Calendar.HOUR);
        int min = temp.get(Calendar.MINUTE);

        String ampm = (temp.get(Calendar.AM_PM) == Calendar.AM) ? "am" : "pm";
        hour = (hour == 0) ? 12 : hour;

        return hour + ":" + minutes + ampm;
    }

    /**
     * compareTo() method
     *
     * @param input the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Date input) {
        int i = input.year * 10000 + input.month *100 + input.day;
        int e = event.get(Calendar.YEAR) * 10000 + event.get(Calendar.MONTH) * 100 + event.get(Calendar.DAY_OF_MONTH);
        int diff = i-e;

        return Integer.compare(diff, 0);
    }



    /**
     * isLeapYear method()
     * @param int year
     * @return boolean true or false
     */
    public boolean isLeapYear(int year) {
        boolean is_Leap = false;
        if(year % 4 == 0) {
            if(year % 100 ==0) {
                if(year % 400==0){
                    is_Leap =true;
                }
            }else{
                is_Leap=true;
            }
        }
        return is_Leap;
    }

    /**
     * isValidDay() method : used to check if the day is valid in a month
     * @param int day
     * @return boolean true or false
     */
    public boolean isValidDay(int year, int month, int day) {
        switch (month){
            case 1, 3, 5, 7, 8 ,10, 12:
                return day <= 31 && day > 0;
            case 4, 6, 9, 11:
                return day <= 30 && day > 0;
            case 2:
                if (isLeapYear(year)) return day <= 29 && day > 0;
                else return day<=28 && day>0;
            default:
                return true;
        }
    }

    /**
     * isValidMonth() method: used to check if the month is valid in a year
     * @param int year
     * @return boolean true or false
     */
    public boolean isValidMonth(int month){
        return month > 0 && month < 13;
    }

    /**
     * isValid() method
     *NOTE: THIS METHOD IS NOT COMPLETE,
     *still need to test for a valid calendar date and return an error, like 13/32/2005
     */
    public boolean isValid() {
        int y = this.year;
        int m = this.month;
        int d = this.day;
        Date thisEvent = new Date(y,m,d);
        //1st step: checks if event date is valid
        if(isValidDay(y, m, d) && isValidMonth(m)){
        //2n step: checks if event date is in the past
            Calendar temp = Calendar.getInstance();
            temp.add(Calendar.MONTH, 6); // add 6 months to current date for next part
            if(event.after(temp)){ // check to see if event date is within 6 months of the current date
                return false;
            }
            else {
                EventCalendar eventCalendar = new EventCalendar();
                Event[] listOfEvents = eventCalendar.getEvent();
                for (Event listOfEvent : listOfEvents) {
                    if (listOfEvent.getDate().compareTo(thisEvent) == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    /**
     * testbed main()
     * @param args
     */
    public static void main(String[] args) {
        Date a = new Date(2023, 9, 31);
        Date b = new Date(2023, 9, 29);
        Date c = new Date(2024, 2, 29);

        Date d = new Date(2024, 2, 29);
        System.out.println(a.isLeapYear(a.year));
        Calendar date = Calendar.getInstance();
//        date.set(2023, 9, 31);
//        int y = date.get(Calendar.YEAR);
//        int m = date.get(Calendar.MONTH);
//        int d = date.get(Calendar.DAY_OF_MONTH);
        System.out.println(a.month+"/"+a.day+"/"+a.year);
        System.out.println(a.isValidMonth(a.month));
        System.out.println(a.isValidDay(a.year, a.month, a.day));
        System.out.println(b.isValidDay(b.year, b.month, b.day));
        System.out.println(c.isValidDay(c.year, c.month, c.day));

        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
        System.out.println(a.compareTo(a));

        System.out.println(c.compareTo(d));




    }
}