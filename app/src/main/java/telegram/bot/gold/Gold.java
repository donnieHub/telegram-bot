package telegram.bot.gold;

import telegram.bot.browser.CbrMain;

import java.io.*;
import java.util.logging.Logger;

public class Gold implements GoldService {

	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private final CbrMain cbrMain = new CbrMain();

	public static void main(String[] args) {
		Gold app = new Gold();
		String price = app.getGoldPrice(CbrMain.TEMP_FILE);
	}

	@Override
	public String getGoldPrice(String fileName) {
		cbrMain.savePricesFromBrowser();

		String goldPrice = "null";
		try (BufferedReader reader = new BufferedReader(new FileReader("./" + fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] prices = line.split(" ");
				goldPrice = prices[prices.length-2].replaceAll("[^\\d.$]", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("goldPrice: " + goldPrice);
		return goldPrice;
	}
}