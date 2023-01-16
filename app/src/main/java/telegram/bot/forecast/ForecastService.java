package telegram.bot.forecast;

public interface ForecastService {

    static ForecastService getInstance() {
        return new ForecastYandex();
    }

    Long getTemp(String cities);

}
