package avaj_launcher;

import java.util.Random;
import avaj_launcher.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private int index;
    private Random random = new Random ();

    private WeatherProvider () {}

    public static WeatherProvider getProvider () {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider ();
        }
        return weatherProvider;
    }

    public String getCurrentWeather (Coordinates coordinates) {
        index = coordinates.getLongitude () + coordinates.getLatitude () + coordinates.getHeight ();
        index += random.nextInt (100);
        index = (index > 0) ? index : -index;
        return weather[index % 4];
    }
}