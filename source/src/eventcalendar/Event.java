package eventcalendar;

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
        return date + " " + startTime + " " + location + " " + contact + " " + duration;
    }

    @Override
    public int compareTo (Event a){
        if (this.date.compareTo(a.date)!=0){
            ret
        }
    }
}
