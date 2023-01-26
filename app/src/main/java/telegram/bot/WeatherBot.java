package telegram.bot;

import static java.util.logging.Level.SEVERE;
import static telegram.bot.Keyboard.keyboardRowMarkup;
import static telegram.bot.forecast.cities.Buttons.inlineMarkup;
import static telegram.bot.forecast.cities.CityName.MOSCOW;
import static telegram.bot.forecast.cities.CityName.OMSK;
import static telegram.bot.forecast.cities.CityName.SAINT_PETERSBURG;

import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands.SetMyCommandsBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.commodities.CommoditiesService;
import telegram.bot.finance.FinanceService;
import telegram.bot.forecast.cities.Buttons;
import telegram.bot.forecast.ForecastService;

public class WeatherBot extends TelegramLongPollingBot implements BotCommands {

    private static final String TOKEN = "Bot-token";
    private static final String BOT_NAME = "omsk55_weather_bot";
    public static Long currentChatId;
    ForecastService weather = ForecastService.getInstance();
    FinanceService finance = FinanceService.getInstance();
    CommoditiesService commodities = CommoditiesService.getInstance();
    Properties property = new Properties();
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WeatherBot(DefaultBotOptions options) {
        super(options);
        Utils.initProperties(property);
        this.setMyCommands();
    }

    public void setMyCommands() {
        SetMyCommandsBuilder builder = SetMyCommands.builder().commands(LIST_OF_COMMANDS);
        builder.scope(new BotCommandScopeDefault());
        builder.languageCode("en");
        builder.build();
        try {
            this.execute(builder.build());
        } catch (TelegramApiException e) {
            logger.log(SEVERE, e.getMessage());
        }
    }

    public String getTemp(String city) {
        return weather.getTemp(city) + "°";
    }

    @Override
    public String getBotUsername() {
        return "@" + BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return getBotTokenFromPropertyFile();
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
                        this.execute(
                            SendMessage.builder().chatId(WeatherBot.currentChatId).text("Здравствуйте " + message.getFrom().getFirstName())
                                .replyMarkup(keyboardRowMarkup()).build());
                        break;
                    case "/forecast":
                        this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                            .text("В каком городе вы хотите узнать погоду?")
                            .replyMarkup(inlineMarkup()).build());
                        break;
                    case "/dollar_exchange_rate":
                        this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                            .text("Курс доллара: " + finance.getDollarExchangeRate() + "₽").build());
                        break;
                    case "/commodities":
                        this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                            .text("Цена на нефть и газ: " + commodities.getCommodityPrice("BRENTOIL").intValue() + "$").build());
                        break;
                    case "/game":
                        this.execute(
                            SendMessage.builder().chatId(WeatherBot.currentChatId).text("Игра в разработке").build());
                        break;
                    case "/help":
                        this.execute(
                            SendMessage.builder().chatId(WeatherBot.currentChatId).text("Список команд:\n"
                                + "/forecast - погода в городе\n"
                                + "/dollar_exchange_rate - курс доллара\n"
                                + "/commodities - цена на нефть и газ\n"
                                + "/game - игра\n"
                                + "/help - помощь\n"
                            ).build());
                        break;
                    default:
                        this.execute(
                            SendMessage.builder().chatId(WeatherBot.currentChatId)
                                .text("Неизвестная команда! Чтобы узнать список команд введите /help")
                                .replyMarkup(Buttons.inlineMarkup())
                                .build());
                }
                logger.info("chatId: " + WeatherBot.currentChatId);
                logger.info("bot_command getFrom: " + message.getFrom().getFirstName());
                logger.info("bot_command: " + message.getEntities().get(0).getType());
                return;
            }
        }
        if (message.hasText()) {
            String messageLine = message.getText();
            logger.info("chatId: " + WeatherBot.currentChatId);
            logger.info("message getFrom: " + message.getFrom().getFirstName());
            logger.info("message text: " + messageLine);
            if (messageLine.contains("Владимир".toLowerCase(Locale.ROOT))) {
                this.execute(SendMessage
                    .builder()
                    .chatId(message.getChatId())
                    .text("Привет Владимир!")
                    .build());
            }
            else if (messageLine.equals("Погода в городе")) {
                this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                    .text("В каком городе вы хотите узнать погоду?")
                    .replyMarkup(inlineMarkup()).build());
            }
            else if (messageLine.equals("Курс доллара")) {
                this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                    .text("Курс доллара: " + finance.getDollarExchangeRate() + "₽").build());
            }
            else if (messageLine.equals("Цена на товары (нефть, газ и тд)")) {
                this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId)
                    .text("Цена на нефть и газ: " + commodities.getCommodityPrice("BRENTOIL").intValue() + "$").build());
            }
            else if (messageLine.equals("Игра")) {
                this.execute(SendMessage.builder().chatId(WeatherBot.currentChatId).text("Игра в разработке").build());
            }
            else if (messageLine.equals("Помощь")) {
                this.execute(
                    SendMessage.builder().chatId(WeatherBot.currentChatId).text("Список команд:\n"
                        + "/forecast - погода в городе\n"
                        + "/dollar_exchange_rate - курс доллара\n"
                        + "/commodities - цена на нефть и газ\n"
                        + "/game - игра\n"
                        + "/help - помощь\n"
                    ).build());
            }
            else {
                this.execute(SendMessage.builder().chatId(message.getChatId())
                    .text("Неизвестный текст сообщения. Чтобы узнать список команд введите /help")
                    .replyMarkup(keyboardRowMarkup()).build());
            }
        }
    }

    private void handle(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        //TODO обрабатывать все кнопки
        String city = callbackQuery.getData();
        switch (city) {
            case "В Омске ":
                city += getTemp(OMSK.toString());
                break;
            case "В Санкт-Петербурге ":
                city += getTemp(SAINT_PETERSBURG.toString());
                break;
            case "В Москве ":
                city += getTemp(MOSCOW.toString());
                break;
            default:
                city += "Температура неизвестна ";
        }

        logger.info("chatId: " + WeatherBot.currentChatId);
        logger.info("callbackQuery getFrom: " + callbackQuery.getFrom().getFirstName());
        logger.info("callbackQuery message: " + message.getText());
        logger.info("cityTemperature: " + city);
        // обновляем кнопки
        this.execute(EditMessageReplyMarkup.builder().chatId(WeatherBot.currentChatId).messageId(message.getMessageId()).replyMarkup(
            InlineKeyboardMarkup.builder().clearKeyboard().build()).build());
        this.execute(SendMessage
            .builder()
            .chatId(message.getChatId())
            .text(city)
            .build());
    }

    private String getBotTokenFromPropertyFile(){
        return property.getProperty(TOKEN);
    }
}
