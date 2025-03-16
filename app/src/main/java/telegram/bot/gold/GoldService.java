package telegram.bot.gold;

public interface GoldService {

    static GoldService getInstance() {
        return new Gold();
    }

    String getGoldPrice(String pathToFile);
}
