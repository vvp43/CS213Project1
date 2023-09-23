package eventcalendar;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    private int find(Event event) {
        events = new Event[numEvents];
        int temp = -1;
        for(int i = 0; i < numEvents; i++){
            if(events[i].compareTo(event)==0) {
                temp = i;
            }
        }
        if(temp != -1){
            return temp;
        }
        else{
            return -1;
        }

    } //search an event in the list
    private void grow() {
        int newNumofEvents = numEvents+4;
        Event[] newEvents = new Event[newNumofEvents];

        for(int i = 0; i < numEvents; i++){
            newEvents[i] = events[i];
        }
        events = newEvents;
        numEvents = newNumofEvents;

    } //increase the capacity by 4
    public boolean add(Event event) {
        int i = 0;
        while(
    }
    public boolean remove(Event event) {

    }
    public boolean contains(Event event) {

    }
    public void print() {

    } //print the array as is
    public void printByDate() {

    } //ordered by date and timeslot
    public void printByCampus() {

    } //ordered by campus and building/room
    public void printByDepartment(){

    } //ordered by department
}
