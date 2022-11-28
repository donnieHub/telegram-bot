package telegram.bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        WeatherBot bot = new WeatherBot(new DefaultBotOptions());
        bot.execute(SendMessage.builder().chatId(269494428L).text("Hello World from Java").build());
    }
}
