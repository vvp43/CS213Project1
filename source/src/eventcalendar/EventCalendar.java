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


    /*

        NOTE: dont know if add works yet, need to figure out if theres an easier way to implement
        add using grow without having to reuse code.

     */
    public boolean add(Event event) {
        int temp = -1;
        for(int i = 0; i < numEvents; i++){
            if(events[i]== null) {
                temp = i;
                break;
            }
        }
        if(temp == -1){
            grow();
            int temp2 = -1;
            for(int i = 0; i < numEvents; i++){
                if(events[i]== null) {
                    temp2 = i;
                    break;
                }
            }
            events[temp2] = event;
            return true;
        }
        else{
            events[temp] = event;
            return true;
        }

    }
//    public boolean remove(Event event) {
//
//    }
//    public boolean contains(Event event) {
//
//    }
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
