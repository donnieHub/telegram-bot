package telegram.bot.forecast.cities;
import static telegram.bot.forecast.cities.CityName.MOSCOW;
import static telegram.bot.forecast.cities.CityName.OMSK;
import static telegram.bot.forecast.cities.CityName.SAINT_PETERSBURG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Buttons {
    private static final List<List<InlineKeyboardButton>> cityButtons = new ArrayList<>();

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
}
