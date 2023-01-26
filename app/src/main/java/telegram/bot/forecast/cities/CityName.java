package telegram.bot.forecast.cities;

public enum CityName {
    OMSK("Омск"), MOSCOW("Москва"), SAINT_PETERSBURG("Санкт-Петербург");
    private String city;

    CityName(String city){
        this.city = city;
    }

    public String getRuCity(){ return city;}
}
