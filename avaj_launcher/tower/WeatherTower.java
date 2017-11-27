package avaj_launcher.tower;

import avaj_launcher.Coordinates;
import avaj_launcher.WeatherProvider;
import avaj_launcher.tower.Tower;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.FileHandler;

public class WeatherTower extends Tower {
    public WeatherTower () {
        super ();
    }

    public String getWeather (Coordinates coordinates) {
        return (WeatherProvider.getProvider ().getCurrentWeather (coordinates));
    }

    private void changeWeather () {
        super.conditionsChanged ();
    }

    public void makeChange () {
        changeWeather ();
    }

    @Override
    public void register (Flyable flyable) {
        super.register (flyable);
        System.out.println ("Weather tower registered: " + flyable.identifySelf ());
        FileHandler.addToBuffer ("Weather tower registered: " + flyable.identifySelf ());
    }

    @Override
    public void unregister (Flyable flyable) {
        System.out.println ("Weather tower de-registered: " + flyable.identifySelf ());
        FileHandler.addToBuffer ("Weather tower de-registered: " + flyable.identifySelf ());
        super.unregister (flyable);
    }
}