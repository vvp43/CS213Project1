package eventcalendar;

import java.sql.SQLOutput;
import java.sql.Time;

/**
 * This class is the main driver, which executes the whole program
 * @author vvp43
 */


public class MainDriver {
    public static void main(String[] args) {
        //new EventOrganizer().run();
        Timeslot a =  Timeslot.MORNING;
        System.out.println(a);
        System.out.printf("%d %d", a.hour, a.minute);
        int h = 10;
        int m = 30;
        if(h == Timeslot.MORNING.hour && m == Timeslot.MORNING.minute){
            System.out.println("BRUH");
        }
        Timeslot b = Timeslot.MORNING;

        System.out.println(a.compareTo(b));

        EventCalendar abc = new EventCalendar();
        Date bruh = new Date(2023, 8, 20);
        Contact ccc = new Contact(Department.CS, "CS@rutgers.edu");
        Event yup = new Event(bruh, Timeslot.MORNING, Location.AB2225, ccc, 60);

        Date bruh2 = new Date(2023, 9, 29);
        Contact ccc2 = new Contact(Department.CS, "EE@rutgers.edu");
        Event yup2 = new Event(bruh2, Timeslot.AFTERNOON, Location.ARC103, ccc2, 30);



        abc.add(yup);
        abc.add(yup2);
        abc.add(yup2);
        abc.print();
        //System.out.println(ccc.isValid());

    }
}
