package avaj_launcher.aircraft;

import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.tower.WeatherTower;
import avaj_launcher.Coordinates;
import avaj_launcher.WeatherProvider;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon () {
        super ();
    }

    private Baloon (String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    public void updateConditions () {
        String weather = weatherTower.getWeather (this.coordinates);
        if (weather == "RAIN") {
            this.coordinates.setHeight ((this.coordinates.getHeight () - 5 > 100) ? 100 : this.coordinates.getHeight () - 5);
            super.log ("Baloon", "It's raining.");
        } else if (weather == "FOG") {
            this.coordinates.setHeight ((this.coordinates.getHeight () - 3 > 100) ? 100 : this.coordinates.getHeight () - 3);
            super.log ("Baloon", "It's foggy.");
        } else if (weather == "SUN") {
            this.coordinates.setHeight ((this.coordinates.getHeight () + 4 > 100) ? 100 : this.coordinates.getHeight () + 4);
            this.coordinates.setLongitude (this.coordinates.getLongitude () + 2);
            super.log ("Baloon", "It's sunny.");
        } else if (weather == "SNOW") {
            this.coordinates.setHeight ((this.coordinates.getHeight () - 15 > 100) ? 100 : this.coordinates.getHeight () - 15);
            super.log ("Baloon", "It's snowing.");
        }
        if (this.coordinates.getHeight () <= 0) {
            super.log ("Baloon", "Landing at: " + this.coordinates.toString ());
            weatherTower.unregister (this);
        }
    }

    public void registerTower (WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    public String identifySelf () {
        return getIdentity ();
    }

    @Override
    public String getIdentity () {
        return "Baloon#" +  super.getIdentity ();
    }
}