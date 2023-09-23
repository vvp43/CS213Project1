package eventcalendar;
import java.util.Calendar;

public class Date implements Comparable<Date> { // <--- no idea what this does
    private int year;
    private int month;
    private int day;
    final Calendar curr = Calendar.getInstance(); // the current year date
    Calendar event = Calendar.getInstance(); // the event date

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        event.set(year, month, day);
    }


    @Override
    public int compareTo(Date input){
        if(event.compareTo(input.event) == 0) {
            return 0;
        }
        else{
            return -1;
        }
    }



    /*
        NOTE: THIS METHOD IS NOT COMPLETE,
        still need to test for a valid calendar date and return an error, like 13/32/2005
     */
    public boolean isValid() {

            if(curr.after(event)){ // checks if event date is in the past
                return false;
            }
            else {
                Calendar temp = Calendar.getInstance();
                temp.add(Calendar.MONTH, 6); // add 6 months to current date for next part
                if(event.after(temp)){ // check to see if event date is within 6 months of the current date
                    return false;
                }
                else {
                    return true;
                }
            }
        }
    }
