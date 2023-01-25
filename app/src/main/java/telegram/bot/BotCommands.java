package telegram.bot;


import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface BotCommands {

    List<BotCommand> LIST_OF_COMMANDS = List.of(
        new BotCommand("/start", "Начать работу"),
        new BotCommand("/forecast", "Погода в городе"),
        new BotCommand("/dollar_exchange_rate", "Курс доллара"),
        new BotCommand("/commodities", "Цена на товары (нефть, газ и тд)"),
        new BotCommand("/game1", "Игра"),
        new BotCommand("/help", "Помощь")
    );

}
