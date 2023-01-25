package telegram.bot.forecast;
import static telegram.bot.forecast.CityName.MOSCOW;
import static telegram.bot.forecast.CityName.OMSK;
import static telegram.bot.forecast.CityName.SAINT_PETERSBURG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Buttons {
    private static final List<List<InlineKeyboardButton>> cityButtons = new ArrayList<>();
    private static final List<KeyboardRow> keyboardRow = new ArrayList<>();

    public static InlineKeyboardMarkup inlineMarkup() {
        cityButtons.clear();
        cityButtons.add(Arrays.asList(
            InlineKeyboardButton.builder().text(OMSK.getRuCity())
                .callbackData("В Омске ").build(),
            InlineKeyboardButton.builder().text(SAINT_PETERSBURG.getRuCity()).
                callbackData("В Санкт-Петербурге ").build(),
            InlineKeyboardButton.builder().text(MOSCOW.getRuCity())
                    .callbackData("В Москве ").build()));
        List<List<InlineKeyboardButton>> rowsInLine = cityButtons;
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);
        return markupInline;
    }

    public static ReplyKeyboardMarkup keyboardRowMarkup() {
        KeyboardRow row = new KeyboardRow();
        row.add("Погода в городе");
        row.add("Курс доллара");
        row.add("Цена на товары \n(нефть, газ и тд)");
        row.add("Игра");
        row.add("Помощь");
        keyboardRow.clear();
        keyboardRow.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRow);
        return replyKeyboardMarkup;
    }
}
