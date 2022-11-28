package telegram.bot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class WeatherBot extends DefaultAbsSender {
    private static final String TOKEN = "5875128546:AAFOh5PcjAJhjXH6a48TVM815POyQCXc6bs";
    private static final String BOT_NAME = "omsk55_weather_bot";

    public WeatherBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
