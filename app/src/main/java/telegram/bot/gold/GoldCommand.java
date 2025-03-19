package telegram.bot.gold;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Sendable;
import telegram.bot.browser.CbrMain;

import java.util.logging.Logger;

public class GoldCommand extends BotCommand implements Sendable {
    public final static String COMMAND = "/gold";
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String user;
    private final GoldService goldService = GoldService.getInstance();

    public void sendAnswer(AbsSender absSender, Long chatId) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText("Цена на золото: " + goldService.getGoldPrice(CbrMain.TEMP_FILE));
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