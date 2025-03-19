package telegram.bot.help;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.KeyboardCommand;
import telegram.bot.Sendable;
import telegram.bot.finance.FinanceCommand;
import telegram.bot.forecast.ForecastCommand;
import telegram.bot.gold.GoldCommand;
import telegram.bot.oil.OilCommand;

public class HelpCommand extends BotCommand implements Sendable {

    public final static String COMMAND = "/help";
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String user;

    public void sendAnswer(AbsSender absSender, Long chatId) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText("Список команд:\n"
                + ForecastCommand.COMMAND + " - погода в городе.\n"
                + FinanceCommand.COMMAND + " - курс доллара.\n"
                + OilCommand.COMMAND + " - цена на нефть.\n"
                + GoldCommand.COMMAND + " - цена на золото.\n"
                + KeyboardCommand.COMMAND + "- показать кнопки.\n"
                + HelpCommand.COMMAND + " - помощь.\n");
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
