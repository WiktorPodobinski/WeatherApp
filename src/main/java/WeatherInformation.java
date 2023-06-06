import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class WeatherInformation {

	public static void displayWeather(String weatherData) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(weatherData).getAsJsonObject();

		// Extract weather parameters
		JsonObject mainObject = jsonObject.getAsJsonObject("main");
		JsonPrimitive temp = mainObject.getAsJsonPrimitive("temp");
		double temperature = temp.getAsDouble();
		double temperatureC = temperature - 273.15;
		JsonPrimitive humidity = mainObject.getAsJsonPrimitive("humidity");

		JsonObject weatherObject = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
		String weatherDescription = weatherObject.get("description").getAsString();

		JsonObject windObject = jsonObject.getAsJsonObject("wind");
		JsonPrimitive windSpeed = windObject.getAsJsonPrimitive("speed");
		JsonPrimitive windDirection = windObject.getAsJsonPrimitive("deg");

		JsonObject cloudsObject = jsonObject.getAsJsonObject("clouds");
		JsonPrimitive cloudiness = cloudsObject.getAsJsonPrimitive("all");

		JsonObject sysObject = jsonObject.getAsJsonObject("sys");
		JsonPrimitive uvIndex = sysObject.getAsJsonPrimitive("uvi");

		// Display weather information
		System.out.println("Weather Information:");
		System.out.println("Temperature: " + temperatureC + "°C");
		System.out.println("Humidity: " + humidity + "%");
		System.out.println("Weather Description: " + weatherDescription);
		System.out.println("Wind Speed: " + windSpeed + " m/s");
		System.out.println("Wind Direction: " + windDirection + "°");
		System.out.println("Cloudiness: " + cloudiness + "%");
		System.out.println("UV Index: " + uvIndex);

		// Display weather icon
		displayWeatherIcon(weatherDescription);
	}

	private static void displayWeatherIcon(String weatherDescription) {
		String weatherIcon = "";

		// Map weather descriptions to icons
		switch (weatherDescription) {
			case "clear sky":
				weatherIcon = "☀️";
				break;
			case "few clouds":
				weatherIcon = "⛅";
				break;
			case "scattered clouds":
				weatherIcon = "☁️";
				break;
			case "broken clouds":
				weatherIcon = "☁️☁️";
				break;
			case "shower rain":
				weatherIcon = "🌦️";
				break;
			case "rain":
				weatherIcon = "🌧️";
				break;
			case "thunderstorm":
				weatherIcon = "⛈️";
				break;
			case "snow":
				weatherIcon = "❄️";
				break;
			case "mist":
				weatherIcon = "🌫️";
				break;
			default:
				weatherIcon = "❓";
				break;
		}

		System.out.println("Icon that best represents the current weather: " + weatherIcon);
	}
}
