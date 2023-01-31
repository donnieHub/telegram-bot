package telegram.bot;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Keyboard {
    private static final List<KeyboardRow> keyboardRow = new ArrayList<>();

    public static ReplyKeyboardMarkup keyboardRowMarkup() {
        KeyboardRow row = new KeyboardRow();
        row.add("Погода в городе");
        row.add("Курс доллара");
        row.add("Цена на товары \n(нефть, газ и тд)");
        row.add("Помощь");
        keyboardRow.clear();
        keyboardRow.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRow);
        return replyKeyboardMarkup;
    }
}
