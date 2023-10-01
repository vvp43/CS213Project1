package eventcalendar;

import java.util.Calendar;

/**
 * This Date class retrieves information
 * about date which contains year, month and day
 *
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
     * getYear() method
     */
    public int getYear() {
        return this.year;
    }

    /**
     * getMonth() method
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * getDay() method
     */
    public int getDay() {
        return this.day;
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
     * vinh
     *
     * @param input the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Date input) {
        int inputDateInt = input.year * 10000 + input.month * 100 + input.day;
        int currentDateInt = this.year * 10000 + this.month * 100 + this.day;
        int diff = currentDateInt - inputDateInt;

        if (diff > 0) {
            return 1;
        }

        if (diff == 0) {
            return 0;
        }

        return -1;
    }


    /**
     * isLeapYear method()
     * checks if year is a leap year
     *
     * @param year to check
     * @return true if it is, false otherwise
     */
    private boolean isLeapYear(int year) {
        boolean is_Leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    is_Leap = true;
                }
            } else {
                is_Leap = true;
            }
        }
        return is_Leap;
    }

    /**
     * isValidDate() method : used to check if the day is valid in a month
     * vinh
     *
     * @param date
     * @return boolean true or false
     */
    private boolean isValidDate(Date date) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        if (month > 0 && month < 13) {
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12:
                    return day <= 31 && day > 0;
                case 4, 6, 9, 11:
                    return day <= 30 && day > 0;
                case 2:
                    if (isLeapYear(year)) return day <= 29 && day > 0;
                    else return day <= 28 && day > 0;
                default:
                    return true;
            }
        }
        return false;
    }

    /**
     * isFutureDate method()
     * Checks if date is in the future or not
     *
     * @param inputDate date to compare
     * @return true if inputDate is in the future, false otherwise
     */
    private boolean isFutureDate(Date inputDate) {
        int currentYear = inputDate.curr.get(Calendar.YEAR);
        int currentMonth = inputDate.curr.get(Calendar.MONTH) + 1;
        int currentDay = inputDate.curr.get(Calendar.DAY_OF_MONTH);
        Date currentDate = new Date(currentYear, currentMonth, currentDay);
        if (inputDate.isValidDate(inputDate)) {
            return inputDate.compareTo(currentDate) > 0;
        }
        return false;
    }

    /**
     * isWithinSixMonths() method
     * Checks if date is within 6 months of the current date
     *
     * @param inputDate date to compare
     * @return true if inputDate is within 6 months of the current date, false otherwise
     */
    private boolean isWithinSixMonths(Date inputDate) {
        if (inputDate.isValidDate(inputDate)) {
            if (isFutureDate(inputDate)) {
                Calendar temp = Calendar.getInstance();
                temp.add(Calendar.MONTH, 7);
                return !inputDate.event.after(temp);
            }
        }

        return false;
    }


    /**
     * isValid() method
     * Checks if an event is valid meaning within 6 months, cannot be in the future, and is an actual date possible.
     *
     * @return true if all conditions above are met, otherwise false
     */
    public boolean isValid() {
        int y = this.year;
        int m = this.month;
        int d = this.day;
        Date thisEvent = new Date(y, m, d);
        //1st step: checks if event date is valid
        if (isValidDate(thisEvent)) {
            //2nd step: checks if event date is in the future
            if (isFutureDate(thisEvent)) {
                //3rd step: check if event date is within 6 months
                if (isWithinSixMonths(thisEvent)) {
                    //4th step: check if event date is duplicated
                    return true;
                } else {
                    System.out.println(m + "/" + d + "/" + y + ": Event date must be within 6 months!");
                    return false;
                }
            } else {
                System.out.println(m + "/" + d + "/" + y + ": Event date must be a future date!");
                return false;
            }
        }
        System.out.println(m + "/" + d + "/" + y + ": Invalid calendar date!");
        return false;
    }

    /**
     * testbed main()
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        Demonstrate test case for isValid()
        isValid() is designed based on three conditions
        1. The date is valid (has to be proper date and month depending on leap or non leap year)
        2. The date is in the future
        3. The date is within 6 months;
         */

        //Example Dates (Today's date: 9/30/2023)
        Date a = new Date(2024, 2, 29);
        Date b = new Date(2023, 2, 29);
        Date c = new Date(2022, 2, 29);
        Date d = new Date(2025, 3, 31);

        //1. The date is valid (has to be proper date and month depending on leap or non leap year)
        System.out.println("* Test Case 1 *");
        System.out.println(a.isValidDate(a));
        System.out.println(b.isValidDate(b));
        System.out.println(c.isValidDate(c));
        System.out.println(d.isValidDate(d));
        System.out.println();

        //2. The date is in the future
        System.out.println("* Test Case 2 *");
        System.out.println(a.isFutureDate(a));
        System.out.println(b.isFutureDate(b));
        System.out.println(c.isFutureDate(c));
        System.out.println(d.isFutureDate(d));
        System.out.println();

        //3. The date is within 6 months
        System.out.println("* Test Case 3 *");
        System.out.println(a.isWithinSixMonths(a));
        System.out.println(b.isWithinSixMonths(b));
        System.out.println(c.isWithinSixMonths(c));
        System.out.println(d.isWithinSixMonths(d));
        System.out.println();

        //isValid() complete method
        System.out.println("Complete method");
        System.out.println(a.isValid());
        System.out.println(b.isValid());
        System.out.println(c.isValid());
        System.out.println(d.isValid());
    }
}