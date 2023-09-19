package eventcalendar;
import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    Calendar curr = Calendar.getInstance(); // the current year date
    Calendar event = Calendar.getInstance(); // the event date
    public boolean isValid() {

        event.set(year, month, day); // initialize event date

        if(curr.after(event)){ // checks if event date is in the past
            return false;
        }
        else {
            curr.add(Calendar.MONTH, 6); // add 6 months to current date for next part
            if(event.after(curr)){ // check to see if event date is within 6 months of the current date
                return false;
            }
            else {
                return true;
            }
        }
    }
}
