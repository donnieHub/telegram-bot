package telegram.bot.start;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Keyboard;
import telegram.bot.Sendable;

public class StartCommand extends BotCommand implements Sendable {

    public final static String COMMAND = "/start";
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String user;

    public void sendAnswer(AbsSender absSender, Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Здравствуйте!");
        message.setReplyMarkup(new Keyboard().getKeyboardRowMarkup());
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
