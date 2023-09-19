package eventcalendar;

/**
 * This enum class lists all the timeslots as constants: MORNING, AFTERNOON, and EVENING.
 * Also, the hours and minutes are included as properties
 * @author Vinh Pham
 */
public enum Timeslot {
    MORNING(10,30),
    AFTERNOON(2,00),
    EVENING(6,30);

    final int hour;
    final int minute;
    /**
     * Property: display hour and minute
     * @param hour: hour of timeslot
     * @param minute: minute of timeslot
     */
    Timeslot (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }


}
