package telegram.bot.oil;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Sendable;
import telegram.bot.browser.YandexMain;

public class OilCommand extends BotCommand implements Sendable {
    public final static String command = "/oil";
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String user;
    SendMessage message;
    OilService oilService = OilService.getInstance();

    public void sendAnswer(AbsSender absSender, Long chatId) {
        message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText("Цена на нефть: " + oilService.getOilPrice(YandexMain.temp));
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