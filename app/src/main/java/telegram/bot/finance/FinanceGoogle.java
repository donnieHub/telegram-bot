package telegram.bot.finance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Properties;
import telegram.bot.Utils;
import telegram.bot.client.MyHttpClient;
import telegram.bot.client.Property;
import telegram.bot.finance.google.json.UsdToRub;

public class FinanceGoogle implements FinanceService, Property {

	private final MyHttpClient myHttpClient;
	private Properties properties = new Properties();
	private final static String URI = "Google-Finance-URI";
	private final static String API_KEY = "Google-Finance-API-Key";

	FinanceGoogle() {
		Utils.initProperties(properties);
		myHttpClient = new MyHttpClient(URI, API_KEY);
	}

	public static void main(String[] args) {
		FinanceGoogle app = new FinanceGoogle();
		Double rate = app.getDollarExchangeRate();
	}


	@Override
	public Double getDollarExchangeRate() {
		HttpRequest request = myHttpClient.createGetRequest(myHttpClient.createURI(
			getUriFromPropertyFile() + "?engine=google_finance&q=USD-Rub&api_key=" + getApiKeyFromPropertyFile()));
		Optional<HttpResponse<String>> response = myHttpClient.getApiResponse(myHttpClient.createClient(), request);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.get().body();
		UsdToRub responseJson = gson.fromJson(json, UsdToRub.class);
		return responseJson.getSummary().getExtractedPrice();
	}

	public String getUriFromPropertyFile(){
		return properties.getProperty(URI);
	}

	public String getApiKeyFromPropertyFile(){
		return properties.getProperty(API_KEY);
	}

}