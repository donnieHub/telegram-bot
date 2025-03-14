package telegram.bot.browser;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import telegram.bot.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class YandexMain {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Properties properties = new Properties();
    private static final String url = "https://dzen.ru";
    private Data data = new Data();
    public final static String temp = "YandexMaindata.txt";

    public YandexMain() {
        Utils.initProperties(properties);
    }

    public static void main(String[] args) throws MalformedURLException {
        YandexMain yandexMain = new YandexMain();
        yandexMain.savePricesFromBrowser();
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
        SelenideElement usdElement = $("a[aria-label='Курс USD/RUB']");
        SelenideElement eurElement = $("a[aria-label='Курс EUR/RUB']");
        SelenideElement oilElement = $("a[aria-label='Курс OIL']");
        data.setOil(parseData(oilElement));
        data.setEur(parseData(eurElement));
        data.setUsd(parseData(usdElement));
        saveData(Arrays.asList(
                data.getUsd() + "₽",
                data.getEur() + "₽",
                data.getOil() + "$"
        ));
        closeWebDriver();
    }

    public double parseData(SelenideElement element) {
        String text = element.$("span").getText();
        logger.info("getData: " + text);
        return Utils.parsePrice(text, "#.##");
    }

    public void saveData(List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("./" + temp, false))) {
            writer.println(data);
        } catch (IOException e) {
            logger.info("saveData: " + data);
            e.printStackTrace();
        }
    }

    class Data {
        private double usd;
        private double eur;
        private double oil;

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getEur() {
            return eur;
        }

        public void setEur(double eur) {
            this.eur = eur;
        }

        public double getOil() {
            return oil;
        }

        public void setOil(double oil) {
            this.oil = oil;
        }
    }
}