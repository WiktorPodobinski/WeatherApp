import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the weather app by !V :--)");
		while (true) {
			System.out.println("Enter a city name: ");
			String city = scanner.nextLine();

			if (city.equalsIgnoreCase("quit")) {
				System.out.println("Exiting the program...");
				break;
			}

			try {
				String weatherData = WeatherAPI.getWeatherData(city);
				WeatherInformation.displayWeather(weatherData);
			} catch (WeatherNotFoundExecption e) {
				System.out.println("City not found. Please enter a valid city name.");
			} catch (IOException e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}

		scanner.close();
	}
}
