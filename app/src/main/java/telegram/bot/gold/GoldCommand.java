package telegram.bot.gold;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.Sendable;
import telegram.bot.browser.CbrMain;

import java.util.Locale;
import java.util.logging.Logger;

public class GoldCommand extends BotCommand implements Sendable {
    public final static String COMMAND = "/gold";
    public final static Double MY_AMOUNT = 70.0;
    public final static Double PRICE_ADJUSTMENT = 0.95753318789;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String user;
    private final GoldService goldService = GoldService.getInstance();

    public void sendAnswer(AbsSender absSender, Long chatId) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        double goldPriceRaw = Double.parseDouble(goldService.getGoldPrice(CbrMain.TEMP_FILE));
        Double myGoldPrice = goldPriceRaw * MY_AMOUNT * PRICE_ADJUSTMENT;
        String myGoldPriceString = String.format("%,.0f", myGoldPrice);
        message.setText("Цена на золото за "
                + String.format(new Locale("ru", "RU"), "%.0f", MY_AMOUNT)
                + " г. - " + myGoldPriceString + " р.");
        try {
            user = absSender.getMe().getFirstName();
            absSender.execute(message);
        } catch (TelegramApiException | AssertionError e) {
            logger.info("sendAnswer chatId: " + chatId);
            logger.info("sendAnswer getFrom: " + user);
            logger.info("sendAnswer message: " + message.getText());
            e.printStackTrace();
        }
    }
}