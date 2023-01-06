package telegram.bot.forecast;

import static java.security.Security.getProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;
import telegram.bot.City;
import telegram.bot.CityName;
import telegram.bot.forecast.YandexApiResponse.YandexWeatherResponse;

public class WeatherAnalysisApplication implements ForecastService {

	Properties property = new Properties();
	private final static String URI = "Yandex-URI";
	private final static String API_KEY = "Yandex-API-Key";
	private City city;

	WeatherAnalysisApplication() {
		initProperties();
	}

	public static void main(String[] args) {
		WeatherAnalysisApplication app = new WeatherAnalysisApplication();

		if (args.length == 0) {
			throw new RuntimeException("Please insert name of the city for example OMSK");
		}

		Long temp = app.getTemp(args[0]);
		System.out.println("Погода в городе " + temp);
	}

	@Override
	public Long getTemp(String userCity) {
		city = this.citySelection(userCity);
		HttpClient client = this.createClient();
		HttpRequest request = this.createGetRequest(this.createURI(
			getUriFromPropertyFile(),"?lat=" + city.getCoord().getLat(), "&lon=" + city.getCoord().getLon()));
		HttpResponse<String> response = null;
		try {
			response = this.getApiResponse(client, request);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.body();
		YandexWeatherResponse responseJson = gson.fromJson(json, YandexWeatherResponse.class);
		return responseJson.getFact().getTemp();
	}

	private City citySelection(String cityName) {
		if (cityName == null && cityName.trim().isEmpty()) {
			city = new City(CityName.SAINT_PETERSBURG, City.cityCoord.get(CityName.SAINT_PETERSBURG));
		} else {
			try {
				CityName.valueOf(cityName);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(
					cityName + " is not the city. Please insert name of the city for example OMSK");
			}
			city = new City(CityName.valueOf(cityName));
		}
		return city;
	}

	private String createURI (String url, String... params) {
		StringBuilder uri = new StringBuilder(url);
		for (String param : params) {
			uri.append(param);
		}
		return uri.toString();
	}

	private HttpClient createClient() {
		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();
		return client;
	}

	private HttpRequest createGetRequest(String uri) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(java.net.URI.create(uri))
				.timeout(Duration.ofMinutes(1))
				.header("Content-Type", "application/json")
				.header("X-Yandex-API-Key", getApiKeyFromPropertyFile())
				.GET()
				.build();
		return request;
	}

	private <T> HttpResponse<T> getApiResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
		HttpResponse<T> response =
				(HttpResponse<T>) client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	private void initProperties() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("secret.properties").getPath();
		try {
			property.load(new FileInputStream(rootPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getUriFromPropertyFile(){
		return property.getProperty(URI);
	}

	private String getApiKeyFromPropertyFile(){
		return property.getProperty(API_KEY);
	}
}
