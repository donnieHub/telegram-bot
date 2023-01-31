package telegram.bot.forecast;
import static java.util.Arrays.asList;
import static telegram.bot.forecast.cities.CityName.MOSCOW;
import static telegram.bot.forecast.cities.CityName.OMSK;
import static telegram.bot.forecast.cities.CityName.SAINT_PETERSBURG;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class CityButtons {

    private static final List<List<InlineKeyboardButton>> cityButtons = new ArrayList<>();

    public InlineKeyboardMarkup display() {
        cityButtons.clear();
        cityButtons.add(asList(
            InlineKeyboardButton.builder().text(OMSK.getRuCity()).callbackData("В Омске ").build(),
            InlineKeyboardButton.builder().text(SAINT_PETERSBURG.getRuCity()).callbackData("В Санкт-Петербурге ").build(),
            InlineKeyboardButton.builder().text(MOSCOW.getRuCity()).callbackData("В Москве ").build()));
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        inlineKeyboard.setKeyboard(cityButtons);
        return inlineKeyboard;
    }
}
