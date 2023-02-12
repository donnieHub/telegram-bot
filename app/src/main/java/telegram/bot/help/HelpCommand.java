package telegram.bot.help;

import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Sendable;
import telegram.bot.commodities.CommoditiesCommand;
import telegram.bot.finance.FinanceCommand;
import telegram.bot.forecast.ForecastCommand;

public class HelpCommand extends BotCommand implements Sendable {

    public final static String command = "/help";
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String user;
    SendMessage message;

    public void sendAnswer(AbsSender absSender, Long chatId) {
        message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText("Список команд:\n"
            + ForecastCommand.command + " - погода в городе.\n"
            + FinanceCommand.command + " - курс доллара.\n"
            + CommoditiesCommand.command + " - цена на нефть и газ.\n"
            + HelpCommand.command + " - помощь.\n");
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
