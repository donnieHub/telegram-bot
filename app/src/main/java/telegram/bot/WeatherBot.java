package telegram.bot;

import static telegram.bot.Utils.filthyWords;
import static telegram.bot.Utils.isFriendName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.apache.http.entity.StringEntity;

public class WeatherBot extends TelegramLongPollingBot {

    private static final String TOKEN = "5875128546:AAFOh5PcjAJhjXH6a48TVM815POyQCXc6bs";
    private static final String BOT_NAME = "omsk55_weather_bot";
    public static Long currentChatId;
    List<List<InlineKeyboardButton>> cityButtons = new ArrayList<>();
    List<List<InlineKeyboardButton>> doneButton = new ArrayList<>();
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WeatherBot(DefaultBotOptions options) {
        super(options);
        cityButtons.add(Arrays.asList(
            InlineKeyboardButton.builder().text(City.OMSK.getRuCity()).callbackData("В Омске -35°C").build(),
            InlineKeyboardButton.builder().text(City.SAINT_PETERSBURG.getRuCity()).callbackData("В Санкт-Петербурге -1°C").build(),
            InlineKeyboardButton.builder().text(City.MOSCOW.getRuCity()).callbackData("В Москве -10°C").build()));
        doneButton.add(Arrays.asList(InlineKeyboardButton.builder().text("Готово!").build()));
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
        if (update.hasCallbackQuery()) {
            try {
                handle(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.hasMessage()) {
            try {
                handle(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handle(Message message) throws TelegramApiException {
        currentChatId = message.getChatId();
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                .filter(e -> e.getType().equalsIgnoreCase("bot_command"))
                .findFirst();
            if (commandEntity.isPresent()) {
                String command = commandEntity.get().getText();
                switch (command) {
                    case "/start":
                        this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId).text("Здравствуйте, как Вас зовут?").build());
                        break;
                    case "/choose_city":
                        this.execute(
                            SendMessage.builder().chatId(WeatherBot.currentChatId).text("В каком городе вы хотите узнать погоду?")
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(cityButtons).build()).build()
                        );
                        break;
                }
                logger.info("chatId: " + WeatherBot.currentChatId);
                logger.info("bot_command: " + message.getEntities().get(0).getType());
                return;
            }
        }
        if (message.hasText()) {
            String messageLine = message.getText();
            logger.info("chatId: " + WeatherBot.currentChatId);
            logger.info("message text: " + messageLine);
            if (messageLine.contains("Владимир".toLowerCase(Locale.ROOT))) {
                this.execute(SendMessage
                    .builder()
                    .chatId(message.getChatId())
                    .text("Привет Владимир!")
                    .build());
            } else if (isFriendName(messageLine) != null) {
                Collections.shuffle(filthyWords);
                this.execute(SendMessage
                    .builder()
                    .chatId(message.getChatId())
                    .text(isFriendName(messageLine) + ", " + filthyWords.get(0).toLowerCase(Locale.ROOT) + " \uD83D\uDE42")
                    .build());
            }
            else {
                this.execute(SendMessage.builder().chatId(message.getChatId()).text("Чтобы узнать погоду используйте команду /choose_city").build());
            }
        }
    }

    private void handle(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        String cityTemperature = callbackQuery.getData();
        logger.info("chatId: " + WeatherBot.currentChatId);
        logger.info("callbackQuery message: " + message.getText());
        logger.info("callbackQuery getFrom: " + callbackQuery.getFrom().getFirstName());
        logger.info("cityTemperature: " + callbackQuery.getData());
        // обновляем кнопки
        this.execute(EditMessageReplyMarkup.builder().chatId(WeatherBot.currentChatId).messageId(message.getMessageId()).replyMarkup(
            InlineKeyboardMarkup.builder().clearKeyboard().build()).build());
        this.execute(SendMessage
            .builder()
            .chatId(message.getChatId())
            .text(cityTemperature)
            .build());
    }
}
