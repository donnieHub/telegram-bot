package telegram.bot.commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Properties;
import telegram.bot.Utils;
import telegram.bot.client.MyHttpClient;
import telegram.bot.client.Property;
import telegram.bot.commodities.commodities.json.CommodityPrice;

public class Commodities implements CommoditiesService, Property {

	private final MyHttpClient myHttpClient;
	private Properties properties = new Properties();
	private final static String URI = "Commodities-URI";
	private final static String API_KEY = "Commodities-API-Key";

	Commodities() {
		Utils.initProperties(properties);
		myHttpClient = new MyHttpClient(URI, API_KEY);
	}

	public static void main(String[] args) {
		Commodities app = new Commodities();
		// TODO добавить товары
		Double price = app.getCommodityPrice("BRENTOIL");
	}


	@Override
	public Double getCommodityPrice(String commodity) {
		HttpRequest request = myHttpClient.createGetRequest(myHttpClient.createURI(
			getUriFromPropertyFile() + "/latest?access_key="+getApiKeyFromPropertyFile()+"&base=USD&symbols="+commodity));
		Optional<HttpResponse<String>> response = myHttpClient.getApiResponse(myHttpClient.createClient(), request);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = response.get().body();
		CommodityPrice responseJson = gson.fromJson(json, CommodityPrice.class);
		return 1 / responseJson.getData().getRates().getBRENTOIL();
	}

	public String getUriFromPropertyFile(){
		return properties.getProperty(URI);
	}

	public String getApiKeyFromPropertyFile(){
		return properties.getProperty(API_KEY);
	}

}