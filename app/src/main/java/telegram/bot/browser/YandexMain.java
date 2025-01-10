package telegram.bot.browser;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class YandexMain {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    final String url = "https://dzen.ru";
    final String containerName = "selenium";
    public final static String fileName = "YandexMaindata.txt";
    Data data = new Data();

    public YandexMain() {
    }

    public static void main(String[] args) throws MalformedURLException {
        YandexMain yandexMain = new YandexMain();
        yandexMain.savePricesFromBrowser();
    }

    public void savePricesFromBrowser() {
        //Configuration.remote = "http://" + containerName + ":4444/wd/hub";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.pageLoadStrategy = "none";
        System.setProperty("chromeoptions.args", "--no-sandbox, --disable-dev-shm-usage, --headless, --disable-gpu, --disable-extensions, --disable-popup-blocking");
        logger.info("Before selenide browser start");
        open(url);
        logger.info("After selenide browser start");
        SelenideElement usdElement = $("a[aria-label='Курс USD/RUB']");
        SelenideElement eurElement = $("a[aria-label='Курс EUR/RUB']");
        SelenideElement oilElement = $("a[aria-label='Курс OIL']");
        data.setOil(getData(oilElement));
        data.setEur(getData(eurElement));
        data.setUsd(getData(usdElement));
        saveData(Arrays.asList(
            data.getUsd().toString() + "₽",
            data.getEur().toString() + "₽",
            data.getOil().toString() + "$"
        ));
        closeWebDriver();
    }

    public BigDecimal getData(SelenideElement element) {
        String text = element.$("span").getText();
        logger.info("getData: " + text);
        return new BigDecimal(text.replaceAll("[^\\d,]", "").replace(',', '.'));
    }

    public void saveData(List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("./" + fileName, false))) {
            writer.println(data);
        } catch (IOException e) {
            logger.info("saveData: " + data);
            e.printStackTrace();
        }
    }

    class Data {
        private BigDecimal usd;
        private BigDecimal eur;
        private BigDecimal oil;

        public BigDecimal getUsd() {
            return usd;
        }

        public void setUsd(BigDecimal usd) {
            this.usd = usd;
        }

        public BigDecimal getEur() {
            return eur;
        }

        public void setEur(BigDecimal eur) {
            this.eur = eur;
        }

        public BigDecimal getOil() {
            return oil;
        }

        public void setOil(BigDecimal oil) {
            this.oil = oil;
        }
    }
}