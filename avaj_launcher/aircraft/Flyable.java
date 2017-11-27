package avaj_launcher.aircraft;

import avaj_launcher.tower.WeatherTower;

public interface Flyable {
    public void updateConditions ();
    public void registerTower (WeatherTower weatherTower);
    public String identifySelf ();
}