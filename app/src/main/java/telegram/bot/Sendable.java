package telegram.bot;

import org.telegram.telegrambots.meta.bots.AbsSender;

public interface Sendable {

    void sendAnswer(AbsSender absSender, Long chatId);

}
