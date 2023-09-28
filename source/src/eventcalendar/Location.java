package eventcalendar;

/**
 * This enum class lists all the locations, which consists of room number as constant names.
 * Building name and campus are properties.
 * @author Vinh Pham
 */
public enum Location {
    HLL114("Hill Center","Busch"),
    ARC103("Alison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "College Avenue"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    final String buildingName;
    final String campus;

    /**
     * Property: Display buidling names and campuses
     * @param buildingName: name of building
     * @param campus: name of campus
     */
    Location (String buildingName, String campus){
        this.buildingName = buildingName;
        this.campus = campus;
    }




}
