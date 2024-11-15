package vacationPlanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Weather {
	private static final String API_KEY = "ed64fe24e8ccd175eb40852e6b3f8799"; // Replace with your OpenWeatherMap API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void getWeather(String city) {
        try {
            String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"; // Metric units
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                System.out.println("Weather in " + city + ": " + jsonResponse.getJSONObject("main").getDouble("temp") + "°C");
            } else {
                System.out.println("Sorry, " + city + " is not a city.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
