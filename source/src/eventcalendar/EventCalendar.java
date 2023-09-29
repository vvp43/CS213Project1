package eventcalendar;

import javax.xml.namespace.QName;

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


    /**
     * events getter
     */
    public Event[] getEvent (){
        return this.events;
    }

    /**
     * find() method
     * @param event
     * @return
     */
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
                events that are equal to the index
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
    public void printByDate() {
        if(events[0] != null) {
            Event[] dateSorted = new Event[numEvents];

            // copy array first
            for (int i = 0; i < numEvents; i++) {
                if (events[i] != null) {
                    dateSorted[i] = events[i];
                }
            }

            boolean swap;
            do {
                swap = false;
                for (int i = 0; i < numEvents - 1; i++) {
                    if (dateSorted[i + 1] != null) {
                        if (dateSorted[i].compareTo(dateSorted[i + 1]) < 0) {
                            Event temp = dateSorted[i];
                            dateSorted[i] = dateSorted[i + 1];
                            dateSorted[i + 1] = temp;
                            swap = true;
                        }
                    }
                }
            } while (swap);

            for(Event i : dateSorted){
                if(i != null){
                    System.out.println(i.toString());
                }
            }
        }

    } //ordered by date and timeslot
    public void printByCampus() {
        if(events[0] != null) {
            Event[] nameSorted = new Event[numEvents];

            // copy array first
            for (int i = 0; i < numEvents; i++) {
                if (events[i] != null) {
                    nameSorted[i] = events[i];
                }
            }

            // sort
            boolean swap;

            do{
                swap = false;
                for (int i = 0; i < numEvents - 1; i++) {
                    if(nameSorted[i+1] != null){
                        if (nameSorted[i].getLocation().buildingName.compareToIgnoreCase(nameSorted[i+1].getLocation().buildingName) > 0) {
                            Event temp = nameSorted[i];
                            nameSorted[i] = nameSorted[i + 1];
                            nameSorted[i + 1] = temp;
                            swap = true;
                        }
                    }
                }
            } while (swap);

            do{
                swap = false;
                for (int i = 0; i < numEvents - 1; i++) {
                    if(nameSorted[i+1] != null){
                        if (nameSorted[i].getLocation().campus.compareToIgnoreCase(nameSorted[i+1].getLocation().campus) > 0) {
                            Event temp = nameSorted[i];
                            nameSorted[i] = nameSorted[i + 1];
                            nameSorted[i + 1] = temp;
                            swap = true;
                        }
                    }
                }
            } while (swap);



            for(Event i : nameSorted){
                if(i != null){
                    System.out.println(i.toString());
                }
            }
        }
        else{
            System.out.println("Event Calendar Empty!");
        }


    } //ordered by campus and building/room
    public void printByDepartment() {
        if (events[0] != null) {
            Event[] deptSorted = new Event[numEvents];

            // copy array first
            for (int i = 0; i < numEvents; i++) {
                if (events[i] != null) {
                    deptSorted[i] = events[i];
                }
            }

            // sort
            boolean swap;

            do {
                swap = false;
                for (int i = 0; i < numEvents - 1; i++) {
                    if (deptSorted[i + 1] != null) {
                        if (deptSorted[i].getContact().getDepartment().toString().compareToIgnoreCase(deptSorted[i + 1].getContact().getDepartment().toString()) > 0) {
                            Event temp = deptSorted[i];
                            deptSorted[i] = deptSorted[i + 1];
                            deptSorted[i + 1] = temp;
                            swap = true;
                        }
                    }
                }
            } while (swap);

            for(Event i : deptSorted){
                if(i != null){
                    System.out.println(i.toString());
                }
            }

        } //ordered by department


    }

public static void main(String[] args) {
    EventCalendar cal = new EventCalendar();

    Event a = new Event(new Date(2023, 9, 30),
            Timeslot.MORNING,
            Location.HLL114,
            new Contact(Department.CS, "cs@rutgers.edu"), 90);

    Event b = new Event(new Date(2023, 9, 29),
            Timeslot.AFTERNOON,
            Location.TIL232,
            new Contact(Department.BAIT, "BAIT@rutgers.edu"), 90);

    Event c = new Event(new Date(2023, 9, 29),
            Timeslot.EVENING,
            Location.ARC103,
            new Contact(Department.ITI, "iti@rutgers.edu"), 90);

    Event d = new Event(new Date(2023, 9, 29),
            Timeslot.MORNING,
            Location.AB2225,
            new Contact(Department.MATH, "math@rutgers.edu"), 90);

    Event e = new Event(new Date(2023, 9, 30),
            Timeslot.AFTERNOON,
            Location.MU302,
            new Contact(Department.EE, "eE@rutgers.edu"), 90);

    Event f = new Event(new Date(2023, 9, 29),
            Timeslot.EVENING,
            Location.BE_AUD,
            new Contact(Department.EE, "EE@rutgers.edu"), 90);
    //test add
    cal.add(a);
    cal.add(b);
    cal.add(c);
    cal.add(d);
    cal.add(e);
    cal.add(f);

    // test print
    cal.print();
    System.out.println("sorted by campus and building");
    cal.printByCampus();

    System.out.println("sorted by department");
    cal.printByDepartment();

    System.out.println("sorted by date and time");
    cal.printByDate();

    }

}


