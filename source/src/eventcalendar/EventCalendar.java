package eventcalendar;

/**
 * This event calendar class uses
 * an array-based implementation of a
 * linear data structure to hold the list of events.
 * @author Seth Yeh
 */
public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;
    private int find(Event event) {
        for(Event i : events){
            if(i != null){
                if(i.equals(event)){
                    return 0;
                }
            }
        }
        return NOT_FOUND;
    } //search an event in the list

    /**
     * grow() method
     */
    private void grow() {
        int newNumofEvents = numEvents+4;
        Event[] newEvents = new Event[newNumofEvents];

        for(int i = 0; i < numEvents; i++){
            newEvents[i] = events[i];
        }
        events = newEvents;
        numEvents = newNumofEvents;

    } //increase the capacity by 4


    /**
        add() method
        NOTE: dont know if add works yet, need to figure out if theres an easier way to implement
        add using grow without having to reuse code.

     */
    public boolean add(Event event) {
        if(numEvents == 0){
            grow();
            events[0] = event;
            return true;
        }


        else {
            if (!contains(event)) {
                int temp = -1;
                for (int i = 0; i < numEvents; i++) {
                    if (events[i] == null) {
                        temp = i;
                        break;
                    }
                }
                if (temp == -1) {
                    grow();
                    int temp2 = -1;
                    for (int i = 0; i < numEvents; i++) {
                        if (events[i] == null) {
                            temp2 = i;
                            break;
                        }
                    }
                    events[temp2] = event;
                    return true;
                } else {
                    events[temp] = event;
                    return true;
                }
            }
            else{
                return false;
            }
        }
    }


    /**
     * remove() method
     * @param event
     * @return
     */
    public boolean remove(Event event) {
        if (!contains(event)) {
            return false;
        }
        else {
            int index = 0;
            for(Event e : events){
                if(e != null){
                    if(e.equals(event)){
                        break;
                    }
                }
                index++;
            }
            /*
                NOTE: idk if this accounts for every case where the number of
                events that are equal to theindex
            */
            if(index == numEvents-1){
                events[index] = null;
            }
            else{
                events[index] = null;
                for(int j = index; j < numEvents-1; j++){
                    if(events[j+1] != null) {
                        events[j] = events[j+1];
                    }
                    else{
                        events[j] = null;
                    }
                }
                return true;
            }

        }
        return true;
    }

    /**
     * contains() method
     * @param event
     * @return
     */
    public boolean contains(Event event) {
        return find(event) != NOT_FOUND;
    }

    /**
     * print() method
     */
    public void print() {
        for(Event i : events){
            if(i != null){
                System.out.println(i.toString());
            }
        }

    } //print the array as is
//    public void printByDate() {
//
//    } //ordered by date and timeslot
//    public void printByCampus() {
//
//    } //ordered by campus and building/room
//    public void printByDepartment(){
//
//    } //ordered by department
}