package telegram.bot.forecast;

public interface ForecastService {

    static ForecastService getInstance() {
        return new ForecastApp();
    }

    Long getTemp(String cities);

}
