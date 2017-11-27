package avaj_launcher.aircraft;

import avaj_launcher.aircraft.Flyable;
import avaj_launcher.aircraft.Helicopter;
import avaj_launcher.aircraft.Baloon;
import avaj_launcher.aircraft.JetPlane;
import avaj_launcher.aircraft.AircraftDetails;
import avaj_launcher.Coordinates;

public class AircraftFactory {
    public AircraftFactory () {}

    public Flyable newAircraft (AircraftDetails aircraftDetails) {
        return newAircraft (
            aircraftDetails.type,
            aircraftDetails.name,
            aircraftDetails.longitude,
            aircraftDetails.latitude,
            aircraftDetails.height
        );
    }

    public Flyable newAircraft (String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates ();
        coordinates.setLongitude (longitude);
        coordinates.setLatitude (latitude);
        coordinates.setHeight (height);
        if (type.equals("Helicopter")) {
            Helicopter helicopter = new Helicopter ();
            helicopter.setName (name);
            helicopter.setCoordinates (coordinates);
            return helicopter;
        } else if (type.equals("Baloon")) {
            Baloon baloon = new Baloon ();
            baloon.setName (name);
            baloon.setCoordinates (coordinates);
            return baloon;
        } else if (type.equals("JetPlane")) {
            JetPlane jetplane = new JetPlane ();
            jetplane.setName (name);
            jetplane.setCoordinates (coordinates);
            return jetplane;
        }
        return null;
    }
}