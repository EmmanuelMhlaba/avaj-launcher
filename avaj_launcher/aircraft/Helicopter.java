package avaj_launcher.aircraft;

import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.tower.WeatherTower;
import avaj_launcher.Coordinates;
import avaj_launcher.WeatherProvider;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter () {
        super ();
    }

    private Helicopter (String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    public void updateConditions () {
        String weather = weatherTower.getWeather (this.coordinates);
        if (weather == "RAIN") {
            this.coordinates.setLongitude (this.coordinates.getLongitude () + 5);
            super.log ("Helicopter", "It's raining.");
        } else if (weather == "FOG") {
            this.coordinates.setLongitude (this.coordinates.getLongitude () + 1);
            super.log ("Helicopter", "It's foggy.");
        } else if (weather == "SUN") {
            this.coordinates.setHeight ((this.coordinates.getHeight () + 2 > 100) ? 100 : this.coordinates.getHeight () + 2);
            this.coordinates.setLongitude (this.coordinates.getLongitude () + 10);
            super.log ("Helicopter", "It's sunny.");
        } else if (weather == "SNOW") {
            this.coordinates.setHeight ((this.coordinates.getHeight () - 12 > 100) ? 100 : this.coordinates.getHeight () - 12);
            super.log ("Helicopter", "It's snowing.");
        }
        if (this.coordinates.getHeight () <= 0) {
            super.log ("Helicopter", "Landing at: " + this.coordinates.toString ());
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
        return "Helicopter#" +  super.getIdentity ();
    }
}