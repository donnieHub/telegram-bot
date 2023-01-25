package telegram.bot.forecast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import telegram.bot.Utils;
import telegram.bot.client.MyHttpClient;
import telegram.bot.client.Property;
import telegram.bot.forecast.yandex.json.YandexWeatherResponse;

public class ForecastYandex implements ForecastService, Property {

	private final MyHttpClient myHttpClient;
	Properties property = new Properties();
	private final static String URI = "Yandex-URI";
	private final static String API_KEY = "Yandex-API-Key";
	private City city;

	ForecastYandex() {
		Utils.initProperties(property);
		myHttpClient = new MyHttpClient(URI, API_KEY);
	}

	public static void main(String[] args) {
		ForecastYandex app = new ForecastYandex();

		if (args.length == 0) {
			throw new RuntimeException("Please insert name of the city for example OMSK");
		}

		Long temp = app.getTemp(args[0]);
		System.out.println("Погода в городе " + temp);
	}

	@Override
	public Long getTemp(String userCity) {
		city = citySelection(userCity);
		String uri = myHttpClient.createURI(
			getUriFromPropertyFile(),"?lat=" + city.getCoord().getLat(), "&lon=" + city.getCoord().getLon());
		HttpClient client = myHttpClient.createClient();
		HttpRequest request = myHttpClient.createGetRequest(uri, "X-Yandex-API-Key", getApiKeyFromPropertyFile());
		HttpResponse<String> response = null;
		try {
			response = myHttpClient.getApiResponse(client, request);
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
					cityName + " is not a city. Please insert name of the city for example OMSK");
			}
			city = new City(CityName.valueOf(cityName));
		}
		return city;
	}

	@Override
	public String getUriFromPropertyFile(){
		return property.getProperty(URI);
	}

	@Override
	public String getApiKeyFromPropertyFile(){
		return property.getProperty(API_KEY);
	}
}
