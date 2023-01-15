package telegram.bot.finance;

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
import telegram.bot.finance.google.UsdToRub;

public class FinanceApp implements FinanceService, Property {

	private final MyHttpClient myHttpClient;
	Properties property = new Properties();
	private final static String URI = "Google-Finance-URI";
	private final static String API_KEY = "Google-Finance-API-Key";

	FinanceApp() {
		Utils.initProperties(property);
		myHttpClient = new MyHttpClient(URI, API_KEY);
	}

	public static void main(String[] args) {
		FinanceApp app = new FinanceApp();

		Double rate = app.getDollarExchangeRate();
		System.out.println("Курс доллара: " + rate);
	}


	@Override
	public Double getDollarExchangeRate() {
		HttpClient client = myHttpClient.createClient();
		HttpRequest request = myHttpClient.createGetRequest(myHttpClient.createURI(
			getUriFromPropertyFile() + "?engine=google_finance&q=USD-Rub&api_key=" + getApiKeyFromPropertyFile()));
		HttpResponse<String> response = null;
		try {
			response = myHttpClient.getApiResponse(client, request);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.body();
		UsdToRub responseJson = gson.fromJson(json, UsdToRub.class);
		return responseJson.getSummary().getExtractedPrice();
	}

	public String getUriFromPropertyFile(){
		return property.getProperty(URI);
	}

	public String getApiKeyFromPropertyFile(){
		return property.getProperty(API_KEY);
	}

}
