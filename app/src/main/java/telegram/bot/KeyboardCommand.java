package telegram.bot;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.finance.FinanceCommand;
import telegram.bot.forecast.ForecastCommand;

public class KeyboardCommand extends BotCommand implements Sendable {

    public final static String COMMAND = "/showKeyboard";
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String user;

    public void sendAnswer(AbsSender absSender, Long chatId) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setReplyMarkup(new Keyboard().getKeyboardRowMarkup());
        message.setText("Кнопки:");
        try {
            user = absSender.getMe().getFirstName();
            absSender.execute(message);
        } catch (TelegramApiException e) {
            logger.info("sendAnswer chatId: " + chatId);
            logger.info("sendAnswer getFrom: " + user);
            logger.info("sendAnswer message: " + message.getText());
            e.printStackTrace();
        }
    }
}
