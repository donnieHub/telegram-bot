package telegram.bot.oil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import telegram.bot.browser.YandexMain;

public class Oil implements OilService {

	YandexMain yandexMain = new YandexMain();

	Oil() {
	}

	public static void main(String[] args) {
		Oil app = new Oil();
		String price = app.getOilPrice(YandexMain.fileName);
		System.out.println("Цена: " + price);
	}

	@Override
	public String getOilPrice(String fileName) {
		yandexMain.savePricesFromBrowser();
		String oilPrice = "null";
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] prices = line.split(" ");
				oilPrice = prices[prices.length-1].replaceAll("[^\\d\\.$]", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oilPrice;
	}

}
