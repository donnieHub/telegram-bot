package telegram.bot.finance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;
import telegram.bot.Utils;
import telegram.bot.finance.google.UsdToRub;

public class FinanceApp implements FinanceService {

	Properties property = new Properties();
	private final static String URI = "Google-Finance-URI";
	private final static String API_KEY = "Google-Finance-API-Key";

	FinanceApp() {
		Utils.initProperties(property);
	}

	public static void main(String[] args) {
		FinanceApp app = new FinanceApp();

		Double rate = app.getDollarExchangeRate();
		System.out.println("Курс доллара: " + rate);
	}


	@Override
	public Double getDollarExchangeRate() {
		HttpClient client = this.createClient();
		HttpRequest request = this.createGetRequest(this.createURI(
			getUriFromPropertyFile() + "?engine=google_finance&q=USD-Rub&api_key=" + getApiKeyFromPropertyFile()));
		HttpResponse<String> response = null;
		try {
			response = this.getApiResponse(client, request);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.body();
		UsdToRub responseJson = gson.fromJson(json, UsdToRub.class);
		return responseJson.getSummary().getExtractedPrice();
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
//				.header("X-Yandex-API-Key", getApiKeyFromPropertyFile())
				.GET()
				.build();
		return request;
	}

	private <T> HttpResponse<T> getApiResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
		HttpResponse<T> response =
				(HttpResponse<T>) client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

	private String getUriFromPropertyFile(){
		return property.getProperty(URI);
	}

	private String getApiKeyFromPropertyFile(){
		return property.getProperty(API_KEY);
	}

}
