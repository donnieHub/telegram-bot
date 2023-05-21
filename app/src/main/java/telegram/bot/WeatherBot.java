package telegram.bot;

import static java.util.logging.Level.SEVERE;
import static telegram.bot.forecast.cities.CityName.MOSCOW;
import static telegram.bot.forecast.cities.CityName.OMSK;
import static telegram.bot.forecast.cities.CityName.SAINT_PETERSBURG;

import java.util.List;
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
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.finance.FinanceCommand;
import telegram.bot.forecast.ForecastCommand;
import telegram.bot.forecast.ForecastService;
import telegram.bot.help.HelpCommand;
import telegram.bot.oil.OilCommand;
import telegram.bot.start.StartCommand;

public class WeatherBot extends TelegramLongPollingBot {

    private static final String TOKEN = "Bot-token";
    private static final String BOT_NAME = "omsk55_weather_bot";
    public static Long currentChatId;
    ForecastService weather = ForecastService.getInstance();
    Sendable forecastCommand = new ForecastCommand();
    Sendable financeCommand = new FinanceCommand();
    Sendable oilCommand = new OilCommand();
    Sendable keyboardCommand = new KeyboardCommand();
    Sendable helpCommand = new HelpCommand();
    Sendable startCommand = new StartCommand();
    Properties property = new Properties();
    List<BotCommand> LIST_OF_COMMANDS = List.of(new BotCommand("/help", "Помощь"));
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
                    case StartCommand.command:
                        startCommand.sendAnswer(this, currentChatId);
                        break;
                    case ForecastCommand.command:
                        forecastCommand.sendAnswer(this, currentChatId);
                        break;
                    case FinanceCommand.command:
                        financeCommand.sendAnswer(this, currentChatId);
                        break;
                    case OilCommand.command:
                        oilCommand.sendAnswer(this, currentChatId);
                        break;
                    case KeyboardCommand.command:
                        keyboardCommand.sendAnswer(this, currentChatId);
                        break;
                    case HelpCommand.command:
                        helpCommand.sendAnswer(this, currentChatId);
                        break;
                    default:
                        this.execute(
                            SendMessage.builder().chatId(currentChatId)
                                .text("Неизвестная команда! Чтобы узнать список команд введите /help")
                                .build());
                }
                logger.info("chatId: " + currentChatId);
                logger.info("bot_command getFrom: " + message.getFrom().getFirstName());
                logger.info("bot_command: " + message.getEntities().get(0).getType());
                return;
            }
        }
        if (message.hasText()) {
            String messageLine = message.getText();
            logger.info("chatId: " + currentChatId);
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
                forecastCommand.sendAnswer(this, currentChatId);
            }
            else if (messageLine.equals("Курс доллара")) {
                financeCommand.sendAnswer(this, currentChatId);
            }
            else if (messageLine.equals("Цена на нефть")) {
                oilCommand.sendAnswer(this, currentChatId);
            }
            else if (messageLine.equals("Помощь")) {
                this.execute(
                    SendMessage.builder().chatId(currentChatId).text("Список команд:\n"
                        + "/forecast - погода в городе\n"
                        + "/dollar_exchange_rate - курс доллара\n"
                        + "/oil - цена на нефть\n"
                        + "/help - помощь\n"
                    ).build());
            }
            else {
                this.execute(SendMessage.builder().chatId(message.getChatId())
                    .text("Неизвестный текст сообщения. Чтобы узнать список команд введите /help")
                    .replyMarkup(new Keyboard().getKeyboardRowMarkup()).build());
            }
        }
    }

    private void handle(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        //TODO обрабатывать все кнопки
        String city = callbackQuery.getData();
        switch (city) {
            case "В Омске ":
                city += weather.getTemp(OMSK.toString());
                break;
            case "В Санкт-Петербурге ":
                city += weather.getTemp(SAINT_PETERSBURG.toString());
                break;
            case "В Москве ":
                city += weather.getTemp(MOSCOW.toString());
                break;
            default:
                city += "Неизвестная команда ";
        }

        logger.info("chatId: " + currentChatId);
        logger.info("callbackQuery getFrom: " + callbackQuery.getFrom().getFirstName());
        logger.info("callbackQuery message: " + message.getText());
        logger.info("cityTemperature: " + city);
        // обновляем кнопки
        this.execute(EditMessageReplyMarkup.builder().chatId(currentChatId).messageId(message.getMessageId()).replyMarkup(
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
