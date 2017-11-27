package avaj_launcher.aircraft;

public class AircraftDetails {
    public String type;
    public String name;
    public int longitude;
    public int latitude;
    public int height;

    public AircraftDetails (String type, String name, int longitude, int latitude, int height) {
        this.type = type;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }
}