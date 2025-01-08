package telegram.bot.oil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import telegram.bot.browser.YandexMain;

public class Oil implements OilService {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	YandexMain yandexMain = new YandexMain();

	Oil() {
	}

	public static void main(String[] args) {
		Oil app = new Oil();
		String price = app.getOilPrice(YandexMain.fileName);
	}

	@Override
	public String getOilPrice(String fileName) {
		yandexMain.savePricesFromBrowser();
		String oilPrice = "null";
		URL filePath = getClass().getClassLoader().getResource(fileName);
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath.getPath()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] prices = line.split(" ");
				oilPrice = prices[prices.length-1].replaceAll("[^\\d\\.$]", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("oilPrice: " + oilPrice);
		return oilPrice;
	}

}