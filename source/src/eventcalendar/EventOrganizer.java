package eventcalendar;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This class is the user interface class to process the command lines.
 * @author Seth Yeh, Vinh Pham
 */
public class EventOrganizer {
    /**
     * isValidCommand()
     * @param String command
     * @return boolean true or false
     */
    public boolean isValidCommand(String command){
        return command.equals("A") || command.equals("R") || command.equals("P")
                || command.equals("PE") || command.equals("PC") || command.equals("PD")
                || command.equals("Q");
    }

    /**
     * createEventObj() method
     */
    private Event createEventObj(String date, String timeSlot, String location, String department, String email, String duration){
        /*
        Date
         */
        //Split date string into array contains month, day, year
        String[] dateArr = date.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        //Create Date object
        Date dateObj = new Date(year, month, day);

        /*
        Timeslot
         */
        Timeslot startTime = null;
        timeSlot = timeSlot.toUpperCase();
        switch(timeSlot){
            case "MORNING":
                startTime = Timeslot.MORNING;
                break;
            case "EVENING":
                startTime = Timeslot.EVENING;
                break;
            case "AFTERNOON":
                startTime = Timeslot.AFTERNOON;
                break;
        }

        /*
        Location
         */
        Location room = null;
        switch(location){
            case "hll114", "HLL114" :
                room = Location.HLL114;
                break;
            case "arc103", "ARC103":
                room = Location.ARC103;
                break;
            case "be_aud", "BE_AUD":
                room = Location.BE_AUD;
                break;
            case "mu302", "MU302":
                room = Location.MU302;
                break;
            case "til232", "TIL232":
                room = Location.TIL232;
                break;
            case "ab2225", "AB2225":
                room = Location.AB2225;
                break;
        }

        /*
        Contact
         */
        //Department
        Department dept = null;
        department = department.toUpperCase();
        switch (department){
            case "CS":
                dept = Department.CS;
                break;
            case "EE":
                dept = Department.EE;
                break;
            case "ITI":
                dept = Department.ITI;
                break;
            case "MATH":
                dept = Department.MATH;
                break;
            case "BAIT":
                dept = Department.BAIT;
                break;
        }
        Contact contact = new Contact(dept, email);


        /*
        Duration
         */
        int timeLength = Integer.parseInt(duration);

        /*
        Create event
         */
        return new Event(dateObj, startTime, room, contact, timeLength);
    }

    /**
     * operationA() method
     */
    private void operationA(Event e, EventCalendar ec){

        /*
        Check if any elements of event is invalid and display error message
         */
        Date date = e.getDate();
        if (!date.isValid()){
            return;
        }

        Timeslot startTime = e.getStartTime();
        if(startTime==null){
            System.out.println("Invalid time slot!");
            return;
        }

        Location location = e.getLocation();
        if(location == null) {
            System.out.println("Invalid location!");
            return;
        }

        Contact contact = e.getContact();
        if(!contact.isValid()) {
            System.out.println("Invalid contact information!");
            return;
        }

        int duration = e.getDuration();
        if(duration<30 || duration > 120){
            System.out.println("Event duration must be at least " +
                    "30 minutes and at most 120 minutes");
            return;
        }

        /*
        Check if the event is duplicated or not
         */
        Event[] eventList = ec.getEvent();
        if(eventList!=null) {
            for (Event i : eventList) {
                if (i.equals(e)) {
                    System.out.println("The event is already on the calendar.");
                    return;
                } else break;
            }
        }

        //Add to event calendar array
        ec.add(e);
        System.out.println("Event added to the calendar.");



    }

    /**
     * operationR() method
     */
    private void operationR(String date, String timeSlot, String location, EventCalendar ec ){
        //Split date string into array contains month, day, year
        String[] dateArr = date.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        //Create Date object
        Date dateObj = new Date(year, month, day);
        /*
        Check if any elements of event is invalid and display error message
         */
        if (!dateObj.isValid()){
            int m = dateObj.getMonth();
            int d = dateObj.getDay();
            int y = dateObj.getYear();
            System.out.println(m+"/"+d+"/"+y+": Invalid calendar date!");
            return;
        }

         /*
        Timeslot
         */
        Timeslot startTime = null;
        timeSlot= timeSlot.toUpperCase();
        switch(timeSlot){
            case "MORNING":
                startTime = Timeslot.MORNING;
                break;
            case "EVENING":
                startTime = Timeslot.EVENING;
                break;
            case "AFTERNOON":
                startTime = Timeslot.AFTERNOON;
                break;
        }

        /*
        Location
         */
        Location room = null;
        switch(location){
            case "hll114", "HLL114" :
                room = Location.HLL114;
                break;
            case "arc103", "ARC103":
                room = Location.ARC103;
                break;
            case "be_aud", "BE_AUD":
                room = Location.BE_AUD;
                break;
            case "mu302", "MU302":
                room = Location.MU302;
                break;
            case "til232", "TIL232":
                room = Location.TIL232;
                break;
            case "ab2225", "AB2225":
                room = Location.AB2225;
                break;
        }

        Event e = new Event(dateObj, startTime, room);
        ec.remove(e);
        System.out.println("Event has been removed from the calendar!");

    }

    /**
     *operationP() method
     */
    private void operationP(EventCalendar ec){
        ec.print();
    }

    /**
     * operationPE() method
     */
    private void operationPE(EventCalendar ec){
        ec.printByDate();
    }

    /**
     * operationPC() method
     */
    private void operationPC(EventCalendar ec){
        ec.printByCampus();
    }

    /**
     * operationPD() method
     */
    private void operationPD(EventCalendar ec){
        ec.printByDepartment();
    }

    /**
     * run() method
     */
    public void run() {
        System.out.println("Event Organizer running...\n");
        Scanner scanObj = new Scanner(System.in);
        //Commands are in uppercase letter(s) and case-sensitive,
        //which means the commands in lowercase letters are invalid.
        boolean programRun = true;
        EventCalendar eventCalendar = new EventCalendar();
        while(programRun){
            String command = scanObj.nextLine();
            String[] inputList = command.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");//split the whole line into elements of String array
            String firstCMD = inputList[0];
            if(!isValidCommand(firstCMD)){
                System.out.println(firstCMD + " is an invalid command!");
            }else{
                switch (firstCMD){
                    case "Q":
                        programRun = false;
                        System.out.println("Event Organizer terminated.");
                        break;
                    case "A":
                        String aDate= inputList[1];
                        String aTimeSlot = inputList[2];
                        String aLocation = inputList[3];
                        String aDepartment = inputList[4];
                        String aEmail = inputList[5];
                        String aDuration = inputList[6];
                        Event aEvent = createEventObj(aDate, aTimeSlot,aLocation,aDepartment,aEmail, aDuration);
                        operationA(aEvent, eventCalendar);
                        break;
                    case "R":
                        String rDate = inputList[1];
                        String rTimeSlot = inputList[2];
                        String rLocation = inputList[3];
                        operationR(rDate, rTimeSlot, rLocation, eventCalendar);
                        break;
                    case "P":
                        operationP(eventCalendar);
                        break;
                    case "PC":
                        operationPC(eventCalendar);
                        break;
                    case "PD":
                        operationPD(eventCalendar);
                        break;
                    case "PE":
                        operationPE(eventCalendar);
                        break;
                }
            }
        }




    }

    public static void main(String[] args) {
//        Scanner scanObj = new Scanner(System.in);
//        String command =scanObj.next();
//        int month = scanObj.nextInt();
//        int day = scanObj.nextInt();
//        int year = scanObj.nextInt();
//        System.out.println(command + " " + day+"/"+month+"/"+year);
//        String str = "10/21/2023";
//        String[] a = str.split("/");
//        for (String i: a){
//            System.out.print(i+" ");
//        }
        new EventOrganizer().run();
    }

}