package eventcalendar;

import java.util.Calendar;

/**
 * This class defines the instance variables, constructors, and methods that are related to Event
 *
 * @author Seth Yeh, Vinh Pham
 */
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact;//include the department email and contact
    private int duration;// in minutes

    /**
     * Constructor with date, startTime, location, contact and duration as Param
     *
     * @param date
     * @param startTime
     * @param location
     * @param contact
     * @param duration
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Constructor with date, startTime, and location as Param
     */
    public Event(Date date, Timeslot startTime, Location location) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }

    /**
     * Date getter method
     *
     * @return Date of event
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Location getter method
     *
     * @return Location of event
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Contact getter method
     *
     * @return Contact info of event
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * startTime getter method
     *
     * @return Starting time of event
     */
    public Timeslot getStartTime() {
        return this.startTime;
    }

    /**
     * Duration getter method
     *
     * @return Duration of event
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * This method is used to calculate endTime
     *
     * @param startHour Starting Time of event
     * @param startMin  Starting Minute of event
     * @param dur       Duration of event
     * @return an array of endTime where endTime[0] is hour and endTime[1] is minute
     */
    public int[] endTime(int startHour, int startMin, int dur) {
        //store start hour and start minute in 2 elements array
        int[] time = new int[2];
        time[0] = startHour;
        time[1] = startMin;
        //add dur to minute in arr
        int sumMin = time[1] + dur;
        //add hr
        time[0] = time[0] + sumMin / 60;
        //new Min
        time[1] = sumMin % 60;
        //return
        return time;
    }

    /**
     * toString() method
     *
     * @return Event and its variables in string format
     */
    @Override
    public String toString() {
        String str1, str2, str3, str4, str5, str6;
        str1 = date.getMonth() + "/" + date.getDay() + "/" + date.getYear();
        if (startTime.minute < 10) {// startTime minute <10 add a 0 infront
            str2 = startTime.hour + ":" + 0 + startTime.minute;
        } else {
            str2 = startTime.hour + ":" + startTime.minute;
        }
        //implements endTime method to get endTime
        int[] endTime = endTime(startTime.hour, startTime.minute, duration);
        if (endTime[1] < 10) { // if endTime minute <10 add a 0
            str3 = endTime[0] + ":" + 0 + endTime[1];
        } else str3 = endTime[0] + ":" + endTime[1];
        str4 = location + " (" + location.buildingName + ", " + location.campus + ")";
        str5 = "" + contact.getDepartment().fullName;
        str6 = "" + contact.getEmail();


        //If startTime at 10:30AM and ends before 12:00PM
        if (startTime == Timeslot.MORNING && duration < 90) {
            return String.format("[Event Date: %1$s] [Start: %2$sam] [End: %3$sam] @%4$s " +
                    "[Contact: %5$s, %6$s]", str1, str2, str3, str4, str5, str6);
            //If startTime at 10:30 AM and ends after 12:00PM
        } else if (startTime == Timeslot.MORNING && duration <= 120) {
            return String.format("[Event Date: %1$s] [Start: %2$sam] [End: %3$spm] @%4$s " +
                    "[Contact: %5$s, %6$s]", str1, str2, str3, str4, str5, str6);
        }
        //If startTime is either AFTERNOON or EVENING
        return String.format("[Event Date: %1$s] [Start: %2$spm] [End: %3$spm] @%4$s " +
                "[Contact: %5$s, %6$s]", str1, str2, str3, str4, str5, str6);
    }

    /**
     * Compares local variable event to given event, e
     *
     * @param e event to compare
     * @return 0 if both date and time are equal, -2 if same date but time is before, 2 if same date but time after, -1 if before date, 1 if after date
     */
    @Override
    public int compareTo(Event e) {
        if (this.date.compareTo(e.date) == 0) {
            if (this.startTime.compareTo(e.startTime) == 0) {
                return 0;//conflicting scheduling
            } else if (this.startTime.compareTo(e.startTime) < 0) {
                return -2;
            } else {
                return 2;
            }
        } else if (this.date.compareTo(e.date) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Checks if two event objects are equal
     *
     * @param event event to compared to
     * @return true if date, startTime, and location are equal, otherwise false
     */
    @Override
    public boolean equals(Object event) {
        Event otherEvent = (Event) event;
        if (this.date.compareTo(otherEvent.date) == 0) {
            if (this.startTime.compareTo(otherEvent.startTime) == 0) {
                return this.location.compareTo(otherEvent.location) == 0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Testbed main()
     */
    public static void main(String[] args) {
        /**
         *Demonstrate equals() method
         */
        Date dateA = new Date(2023, 02, 24);
        Timeslot startTimeA = Timeslot.MORNING;
        Location locationA = Location.HLL114;
        Event eventA = new Event(dateA, startTimeA, locationA);

        Date dateB = new Date(2023, 02, 24);
        Timeslot startTimeB = Timeslot.MORNING;
        Location locationB = Location.HLL114;
        Event eventB = new Event(dateB, startTimeB, locationB);

        Date dateC = new Date(2023, 02, 24);
        Timeslot startTimeC = Timeslot.AFTERNOON;
        Location locationC = Location.HLL114;
        Event eventC = new Event(dateC, startTimeC, locationC);

        System.out.println(eventA.equals(eventB));
        System.out.println(eventA.equals(eventC));
        System.out.println(eventB.equals(eventC));

    }
}