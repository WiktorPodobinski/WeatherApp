import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {


	private static final String API_KEY = "80a999e2e3a41d1f8d061a1e03526168";

	public static String getWeatherData(String city) throws IOException, WeatherNotFoundExecption {
		String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;

		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
			throw new WeatherNotFoundExecption("City not found. Please input a valid city name!");
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			response.append(line);
		}

		reader.close();
		connection.disconnect();

		return response.toString();
	}
}
