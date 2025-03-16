package telegram.bot.browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import telegram.bot.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;
import static telegram.bot.Utils.createFileIfNotExist;

public class CbrMain {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Properties properties = new Properties();
    private static final String url = "https://www.cbr.ru/hd_base/metall/metall_base_new/";
    private Data data = new Data();
    public final static String temp = "CbrMainData.txt";

    public CbrMain() {
        Utils.initProperties(properties);
    }

    public static void main(String[] args) throws MalformedURLException {
        CbrMain cbrMain = new CbrMain();
        cbrMain.savePricesFromBrowser();
    }

    public void savePricesFromBrowser() {
        String mode = properties.getProperty("mode", SeleniumMode.REMOTE.getMode());
        if (mode.equals(SeleniumMode.REMOTE.getMode())) {
            System.setProperty("chromeoptions.args", "--no-sandbox, --disable-dev-shm-usage, --headless, --disable-gpu, --disable-extensions, --disable-popup-blocking");
        }
        Configuration.remote = properties.getProperty("Selenium-URL-" + mode);
        Configuration.browser = properties.getProperty("Selenium-Browser");
        Configuration.timeout = Long.parseLong(properties.getProperty("Selenium-Timeout"));
        Configuration.pageLoadTimeout = Long.parseLong(properties.getProperty("Selenium-PageLoadTimeout"));
        Configuration.pageLoadStrategy = properties.getProperty("Selenium-PageLoadStrategy");
        logger.info("Before selenide browser start");
        open(url);
        logger.info("After selenide browser start");

        SelenideElement table = $("table.data");
        ElementsCollection tableRows = table.$$("tr");
        SelenideElement tableHeaderElement = tableRows.get(0);
        SelenideElement lastDateElement = tableRows.get(1);
        SelenideElement goldElement = lastDateElement.$$("td.right").get(0);
        SelenideElement silverElement = lastDateElement.$$("td.right").get(1);
        SelenideElement platinumElement = lastDateElement.$$("td.right").get(2);
        SelenideElement palladiumElement = lastDateElement.$$("td.right").get(3);

        data.setPlatinum(parseData(goldElement));
        data.setSilver(parseData(silverElement));
        data.setGold(parseData(platinumElement));
        data.setGold(parseData(palladiumElement));
        saveData(Arrays.asList(
                data.getGold() + "₽",
                data.getSilver() + "₽",
                data.getPlatinum() + "₽",
                data.getPalladium() + "₽"
        ));
        closeWebDriver();
    }

    public double parseData(SelenideElement element) {
        String text = element.getText();
        logger.info("getData: " + text);
        return Utils.parsePrice(text, "#.##");
    }

    public void saveData(List<String> data) {
        createFileIfNotExist(temp);

        try (PrintWriter writer = new PrintWriter(new FileWriter("./" + temp, false))) {
            writer.println(data);
        } catch (IOException e) {
            logger.info("saveData: " + data);
            e.printStackTrace();
        }
    }

    class Data {
        private double gold;
        private double silver;
        private double platinum;
        private double palladium;

        public double getGold() {
            return gold;
        }

        public void setGold(double gold) {
            this.gold = gold;
        }

        public double getSilver() {
            return silver;
        }

        public void setSilver(double silver) {
            this.silver = silver;
        }

        public double getPlatinum() {
            return platinum;
        }

        public void setPlatinum(double platinum) {
            this.platinum = platinum;
        }

        public double getPalladium() {
            return palladium;
        }

        public void setPalladium(double palladium) {
            this.palladium = palladium;
        }
    }
}