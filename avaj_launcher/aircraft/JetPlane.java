package avaj_launcher.aircraft;

import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.tower.WeatherTower;
import avaj_launcher.Coordinates;
import avaj_launcher.WeatherProvider;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane () {
        super ();
    }

    private JetPlane (String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    public void updateConditions () {
        String weather = weatherTower.getWeather (this.coordinates);
        if (weather == "RAIN") {
            this.coordinates.setLatitude (this.coordinates.getLatitude () + 5);
            super.log ("JetPlane", "It's raining.");
        } else if (weather == "FOG") {
            this.coordinates.setLatitude (this.coordinates.getLatitude () + 1);
            super.log ("JetPlane", "It's foggy.");
        } else if (weather == "SUN") {
            this.coordinates.setHeight ((this.coordinates.getHeight () + 2 > 100) ? 100 : this.coordinates.getHeight () + 2);
            this.coordinates.setLatitude (this.coordinates.getLatitude () + 10);
            super.log ("JetPlane", "It's sunny.");
        } else if (weather == "SNOW") {
            this.coordinates.setHeight ((this.coordinates.getHeight () - 7 > 100) ? 100 : this.coordinates.getHeight () - 7);
            super.log ("JetPlane", "It's snowing.");
        }
        if (this.coordinates.getHeight () <= 0) {
            super.log ("JetPlane", "Landing at: " + this.coordinates.toString ());
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
        return "JetPlane#" +  super.getIdentity ();
    }
}