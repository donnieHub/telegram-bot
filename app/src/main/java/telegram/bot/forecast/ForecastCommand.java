package telegram.bot.forecast;

import static telegram.bot.forecast.cities.Buttons.inlineMarkup;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Sendable;

public class ForecastCommand extends BotCommand implements Sendable {
    String user;
    SendMessage message;
    String messageText = "*В каком городе вы хотите узнать погоду?*";
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void sendAnswer(AbsSender absSender, Long chatId) {
        message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(messageText);
        message.setReplyMarkup(inlineMarkup());
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
