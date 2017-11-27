package avaj_launcher;

import java.util.List;
import java.util.ArrayList;
import avaj_launcher.Scenario;
import avaj_launcher.aircraft.AircraftDetails;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.tower.WeatherTower;

public class AvajLauncher {

    public static void avajLauncher (String filename) {
        Scenario scenario = new Scenario (filename);
        WeatherTower weatherTower = new WeatherTower ();
        List<Flyable> flys = new ArrayList<Flyable> ();
        AircraftFactory aircraftFactory = new AircraftFactory ();
        if (scenario.scenarioValid () == true) {
            for (AircraftDetails aircraft : scenario.getAircraftDetails ()) {
                Flyable f = aircraftFactory.newAircraft (aircraft);
                if (f != null) {
                    weatherTower.register (f);
                    f.registerTower (weatherTower);
                }
            }
            for (int i = 0; i < scenario.getScenarioLoop (); i++) {
                weatherTower.makeChange ();
            }
        }
        FileHandler.writeSimFile ("simulation.txt");
    }

    public static void main (String[] args) {
        if (args.length == 1) {
            avajLauncher (args[0]);
        } else {
            if (args.length == 0) {
                System.out.println ("Please specify a file.");
            } else {
                System.out.println ("Please specify only one file.");
            }
        }
    }
}
