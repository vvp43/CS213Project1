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
        EventCalendar eventidoro = new EventCalendar();

    }
}
