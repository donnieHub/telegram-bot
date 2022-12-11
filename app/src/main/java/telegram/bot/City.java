package telegram.bot;

public enum City {
    OMSK("Омск"), MOSCOW("Москва"), SAINT_PETERSBURG("Санкт-Петербург");
    private String city;

    City(String city){
        this.city = city;
    }

    public String getRuCity(){ return city;}
}
