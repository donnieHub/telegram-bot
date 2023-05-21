package telegram.bot.oil;

public interface OilService {

    static OilService getInstance() {
        return new Oil();
    }

    String getOilPrice(String pathToFile);
}
