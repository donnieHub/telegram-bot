package telegram.bot;

import java.util.Arrays;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Keyboard {
    private static final String WEATHER_BTN_TEXT = "Погода в городе";
    private static final String DOLLAR_PRICE_BTN_TEXT = "Курс доллара";
    private static final String OIL_PRICE_BTN_TEXT = "Цена на нефть";
    private static final String HELP_BTN_TEXT = "Помощь";

    private static final List<KeyboardRow> KEYBOARD_ROWS = Arrays.asList(
        createKeyboardRow(WEATHER_BTN_TEXT),
        createKeyboardRow(DOLLAR_PRICE_BTN_TEXT),
        createKeyboardRow(OIL_PRICE_BTN_TEXT),
        createKeyboardRow(HELP_BTN_TEXT)
    );

    public ReplyKeyboardMarkup getKeyboardRowMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(KEYBOARD_ROWS);
        return replyKeyboardMarkup;
    }

    private static KeyboardRow createKeyboardRow(String buttonText) {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(buttonText));
        return row;
    }
}
