package telegram.bot;

import java.io.IOException;

import java.util.logging.Logger;

import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static java.util.logging.Level.SEVERE;

public class Main {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws TelegramApiException, IOException {

        MyLogger.create();

        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
            logger.log(SEVERE, "Фатальная ошибка в потоке " + thread.getName(), e);
            System.exit(1);
        });

        DefaultBotOptions options = new DefaultBotOptions();
        options.setRequestConfig(RequestConfig.custom()
                .setConnectTimeout(15_000)
                .setConnectionRequestTimeout(15_000)
                .setSocketTimeout(70_000)   // больше getUpdatesTimeout (50 с)
                .build());

        WeatherBot bot = new WeatherBot(options);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}