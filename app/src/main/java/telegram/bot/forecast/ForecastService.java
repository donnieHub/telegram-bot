package telegram.bot.forecast;

public interface ForecastService {

    static ForecastService getInstance() {
        return new WeatherAnalysisApplication();
    }

    Long getTemp(String cities);

}
