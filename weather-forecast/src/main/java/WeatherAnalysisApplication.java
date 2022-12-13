import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import json.YandexApiResponse.YandexWeatherResponse;

public class WeatherAnalysisApplication {

	//TODO вынести в properties
	private final static String URI = "https://api.weather.yandex.ru/v2/informers";

	public static void main(String[] args) throws IOException, InterruptedException {

		WeatherAnalysisApplication app = new WeatherAnalysisApplication();
		City city = app.citySelection(args);
		HttpClient client = app.createClient();
		HttpRequest request = app.createGetRequest(
				app.createURI(
						URI,
						"?lat=" + city.getCoord().getLat(),
						"&lon=" + city.getCoord().getLon()));


		HttpResponse<String> response = app.getApiResponse(client, request);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.body();
		YandexWeatherResponse responseJson = gson.fromJson(json, YandexWeatherResponse.class);

		System.out.println("Погода в городе " + city.getCityName());
		System.out.println(response.statusCode());
		System.out.println(response.body());
		System.out.println("--------------------------------------------------------");
		System.out.println(responseJson.getFact().getTemp());
	}

	private City citySelection(String args[]) {
		City city = null;
		if (args.length == 0) {
			city = new City(CityName.SPB, City.cityCoord.get(CityName.SPB));
		} else {
			try {
				CityName.valueOf(args[0]);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(args[0] + " is not the city. Please insert name of the city for example OMSK");
			}
			city = new City(CityName.valueOf(args[0]));
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
				//TODO вынести в properties
				.header("X-Yandex-API-Key", "bd804162-bf13-4baa-9508-27b3ba88c782")
				.GET()
				.build();
		return request;
	}

	private <T> HttpResponse<T> getApiResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
		HttpResponse<T> response =
				(HttpResponse<T>) client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}
}
