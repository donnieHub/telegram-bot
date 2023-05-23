package telegram.bot.browser;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class YandexMain {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    final String url = "https://dzen.ru/";
    public final static String fileName = "YandexMaindata.txt";
    Data data = new Data();

    public YandexMain() {
    }

    public static void main(String[] args) throws MalformedURLException {
        YandexMain yandexMain = new YandexMain();
        yandexMain.savePricesFromBrowser();
    }

    public void savePricesFromBrowser() {
        Configuration.headless = true;
        open(url);
        SelenideElement container = $("article.currency-rates__container-3P");
        container.shouldBe(visible);
        SelenideElement usdElement = container.$("a[aria-label='Курс USD/RUB']");
        SelenideElement eurElement = container.$("a[aria-label='Курс EUR/RUB']");
        SelenideElement oilElement = container.$("a[aria-label='Курс OIL']");
        data.setOil(getData(oilElement));
        data.setEur(getData(eurElement));
        data.setUsd(getData(usdElement));
        saveData(Arrays.asList(
            data.getUsd().toString() + "₽",
            data.getEur().toString() + "₽",
            data.getOil().toString() + "$"
        ));
    }

    public BigDecimal getData(SelenideElement element) {
        String text = element.$("span[class^='currency-rates__itemValue']").getText();
        logger.info("getData: " + text);
        return new BigDecimal(text.replaceAll("[^\\d,]", "").replace(',', '.'));
    }

    public void saveData(List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            writer.println(data);
        } catch (IOException e) {
            logger.info("saveData: " + data);
            e.printStackTrace();
        }
    }

    class Data {
        BigDecimal usd;
        BigDecimal eur;
        BigDecimal oil;

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

