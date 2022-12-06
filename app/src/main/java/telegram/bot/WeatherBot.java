package telegram.bot;

import static telegram.bot.StringUtils.filthyWords;
import static telegram.bot.StringUtils.isFriendName;

import java.util.Collections;
import java.util.Locale;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WeatherBot extends TelegramLongPollingBot {

    private static final String TOKEN = "5875128546:AAFOh5PcjAJhjXH6a48TVM815POyQCXc6bs";
    private static final String BOT_NAME = "omsk55_weather_bot";
    public static Long currentChatId;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WeatherBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return "@" + BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            currentChatId = message.getChatId();
            if (message.hasEntities()) {
                if (message.getEntities().get(0).getType().equalsIgnoreCase("bot_command")) {
                try {
                    this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId).text("Здравствуйте, как Вас зовут?").build());
                    logger.info("chatId: " + WeatherBot.currentChatId);
                    logger.info("bot_command: " + message.getEntities().get(0).getType());
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            }
            if (message.hasText()) {
                String messageLine = message.getText();
                logger.info("chatId: " + WeatherBot.currentChatId);
                logger.info("message text: " + messageLine);
                if (messageLine.contains("Владимир".toLowerCase(Locale.ROOT))) {
                    try {
                        this.execute(SendMessage
                            .builder()
                            .chatId(message.getChatId())
                            .text("Привет Владимир!")
                            .build());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (isFriendName(messageLine) != null) {
                    try {
                        Collections.shuffle(filthyWords);
                        this.execute(SendMessage
                            .builder()
                            .chatId(message.getChatId())
                            .text(isFriendName(messageLine) + ", " + filthyWords.get(0).toLowerCase(Locale.ROOT) + " \uD83D\uDD95\uD83C\uDFFC")
                            .build());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        this.execute(SendMessage.builder().chatId(message.getChatId()).text("Иди нахуй!").build());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
