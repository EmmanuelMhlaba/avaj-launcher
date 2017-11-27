package avaj_launcher.aircraft;

import avaj_launcher.Coordinates;
import avaj_launcher.FileHandler;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    public Aircraft () {
        this.id = nextId ();
    }

    protected Aircraft (String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId ();
    }

    private long nextId () {
        return ++idCounter;
    }

    protected void log (String type, String message) {
        System.out.println (type + "#" + this.name + "(" + this.id + "): " + message);
        FileHandler.addToBuffer (type + "#" + this.name + "(" + this.id + "): " + message);
    }

    public long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public Coordinates getCoordinates () {
        return coordinates;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setCoordinates (Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getIdentity () {
        return this.name + "(" + this.id + ")";
    }
}