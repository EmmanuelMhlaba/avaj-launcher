package avaj_launcher;

import java.util.List;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import avaj_launcher.FileHandler;
import avaj_launcher.aircraft.AircraftDetails;

public class Scenario {
    private List<String> scenarioList = null;
    private int scenarioLoop = 0;
    private List<AircraftDetails> aircraft = null;
    private boolean valid = false;

    public Scenario (String filename) {
        scenarioList = FileHandler.readFile (filename);
        aircraft = new ArrayList<AircraftDetails> ();
        if (scenarioList.size () > 0) {
            validateScenario ();
        } else {
            System.out.println ("Error while reading the scenario file.");
        }
    }

    private void validateScenario () {
        int loop = 0;
        valid = true;
        if (scenarioList != null) {
            for (String line : scenarioList) {
                if (loop != 0) {
                    line = line.trim ();
                    String[] tmp = line.split (" ");
                    if (tmp.length != 5) {
                        System.out.print ("Error in scenario file at line: ");
                        System.out.println (loop);
                        valid = false;
                    } else {
                        if (addDetails (tmp) == false) {
                            System.out.print ("Error in scenario file at line: ");
                            System.out.println (loop);
                        }
                    }
                } else {
                    try {
                        scenarioLoop = Integer.parseInt (line.trim ());
                    } catch (NumberFormatException e) {
                        System.out.println ("Error: " + e.getMessage ());
                        valid = false;
                    }
                }
                loop++;
            }
        }
    }

    private boolean addDetails (String[] details) {
        try {
            aircraft.add (new AircraftDetails(
                details[0],
                details[1],
                Integer.parseInt (details[2]),
                Integer.parseInt (details[3]),
                Integer.parseInt (details[4])
            ));
        } catch (NumberFormatException e) {
            System.out.println ("Error: " + e.getMessage ());
            return false;
        }
        return true;
    }

    public boolean scenarioValid () {
        return valid;
    }

    public int getScenarioLoop () {
        return scenarioLoop;
    }

    public List<AircraftDetails> getAircraftDetails () {
        return aircraft;
    }
}