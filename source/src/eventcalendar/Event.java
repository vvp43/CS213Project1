package eventcalendar;

import java.util.Calendar;

/**
 * This class defines the instance variables, constructors, a
 * @author vvp43
 */
public class Event implements Comparable<Event>{
    private Date date; //the event date
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    @Override
    public String toString(){
        return "[Event Date: "+(date.curr.get(Calendar.MONTH)+1)+"/" +(date.curr.get
                (Calendar.DAY_OF_MONTH))+"/"+(date.curr.get(Calendar.YEAR))+

                "][Start: "+startTime.hour+":"+startTime.minute+"" //not able to implement am/pm yet

                ;
        // psuedocode reference
        // return "[Event Date: "date + "][Start: " + startTime + "]
        // [End: " + startTime+duration + "]@" + location + "[" + contact + "]";
    }

    @Override
    /*
        Not sure what to return if this compareTo fails yet so i put in 1s for a temporary solution
     */
    public int compareTo (Event a){
        if (this.date.compareTo(a.date)==0){
            if(this.startTime.compareTo(a.startTime)==0){
                return 0; // conflicting scheduling
            }
            else{
                return 1;
            }
        }
        else{
            return 1;
        }
    }
}
