package eventcalendar;

/**
 * This enum class lists all the timeslots as constants: MORNING, AFTERNOON, and EVENING.
 * Also, the hours and minutes are included as properties
 * @author Vinh Pham
 */
public enum Timeslot {
    MORNING(10,30, "am"),
    AFTERNOON(2,00, "pm"),
    EVENING(6,30, "pm");

    final int hour;
    final int minute;

    final String ampm;
    /**
     * Property: display hour and minute
     * @param hour: hour of timeslot
     * @param minute: minute of timeslot
     */
    Timeslot (int hour, int minute, String ampm) {
        this.hour = hour;
        this.minute = minute;
        this.ampm = ampm;
    }


}
