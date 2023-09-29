package eventcalendar;

import java.sql.SQLOutput;
import java.util.Calendar;

/**
 * This class defines the instance variables, constructors, a
 * @author Seth Yeh, Vinh Pham
 */
public class Event implements Comparable<Event>{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact;//include the department email and contact
    private int duration;// in minutes

    /**
     * Constructor with date, startTime, location, contact and duration as Param
     * @param date
     * @param startTime
     * @param location
     * @param contact
     * @param duration
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Date getter method
     */
    public Date getDate (){
        return this.date;
    }

    /**
     * This method is used to calculate endTime
     * @param  int duration in min
     * @return an array of endTime where endTime[0] is hour and endTime[1] is minute
     */
    public int[] endTime(int startHour, int startMin, int dur){
        //store start hour and start minute in 2 elements array
        int[] time = new int[2];
        time[0]= startHour;
        time[1]= startMin;
        //add dur to minute in arr
        int sumMin= time[1] + dur;
        //add hr
        time[0]= time[0]+ sumMin/60;
        //new Min
        time[1]= sumMin%60;
        //return
        return time;
    }

    /**
     * toString() method
     * @return String format
     */
    @Override
    public String toString(){
        String str1, str2, str3, str4, str5, str6;
        str1=date.event.get(Calendar.MONTH)+"/"+date.event.get(Calendar.DAY_OF_MONTH)+"/"+date.event.get(Calendar.YEAR);
        if(startTime.minute<10) {// startTime minute <10 add a 0 infront
            str2 = startTime.hour + ":" + 0 + startTime.minute;
        } else {str2 = startTime.hour + ":" + startTime.minute;}
        //implements endTime method to get endTime
        int[] endTime = endTime(startTime.hour, startTime.minute, duration);
        if  (endTime[1] < 10){ // if endTime minute <10 add a 0
            str3= endTime[0]+":" + 0 + endTime[1];
        } else str3= endTime[0] + ":" + endTime[1];
        str4 = location + "(" + location.buildingName + ", " +location.campus + ")";
        str5 = "" + contact.getDepartment();
        str6 = "" + contact.getEmail();


        //If startTime at 10:30AM and ends before 12:00PM
        if(startTime==Timeslot.MORNING && duration<90) {
            return String.format("[Event Date:%1$s][Start:%2$sam][End:%3$sam]@%4$s" +
                                "[Contact:%5$s,%6$s]",str1, str2, str3, str4, str5, str6);
        //If startTime at 10:30 AM and ends after 12:00PM
        }else if(startTime == Timeslot.MORNING && duration <= 120){
            return String.format("[Event Date:%1$s][Start:%2$sam][End:%3$spm]@%4$s" +
                    "[Contact:%5$s,%6$s]",str1, str2, str3, str4, str5, str6);
        }
        //If startTime is either AFTERNOON or EVENING
        return String.format("[Event Date:%1$s][Start:%2$spm][End:%3$spm]@%4$s" +
                "[Contact:%5$s,%6$s]",str1, str2, str3, str4, str5, str6);
    }

    /**
     * compareTo() method
     *Not sure what to return if this compareTo fails yet so i put in -1s for a temporary solution
     */
    @Override
    public int compareTo (Event a) {
        if(this.date.compareTo(a.date)==0){
            if(this.startTime.compareTo(a.startTime)==0){
                return 0;//conflicting scheduling
            }
        }
        return -1;
    }

    /**
     * equals() method
     */
    @Override
    public boolean equals(Object o){
        if(o.equals(this)){
            return true;
        }

      if(!(o instanceof Event)){
          return false;
      }

      Event c = (Event) o;

      return this.date.compareTo(c.date) == 0 && this.startTime.compareTo(c.startTime)==0
              && this.location.compareTo(c.location) == 0;

    }

    /**
     * Testbed main() for Event
     * @param args
     */
    public static void main(String[] args) {
        Event a = new Event(new Date(2023, 9, 29),
                Timeslot.AFTERNOON,
                Location.HLL114,
                new Contact(Department.CS, "cs@rutgers.edu"), 90);

        Event b = new Event(new Date(2023, 9, 29),
                Timeslot.AFTERNOON,
                Location.HLL114,
                new Contact(Department.CS, "cs@rutgers.edu"), 90);

        Event c = new Event(new Date(2023, 9, 29),
                Timeslot.EVENING,
                Location.HLL114,
                new Contact(Department.CS, "cs@rutgers.edu"), 90);

        Event d = new Event(new Date(2023, 9, 29),
                Timeslot.MORNING,
                Location.HLL114,
                new Contact(Department.CS, "cs@rutgers.edu"), 90);
        System.out.println(a.toString());

        System.out.println(a.compareTo(d)); // should be 1

        System.out.println(a.compareTo(c));

        System.out.println(a.compareTo(b));



    }
}
