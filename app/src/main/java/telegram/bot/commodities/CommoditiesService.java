package telegram.bot.commodities;

public interface CommoditiesService {

    static CommoditiesService getInstance() {
        return new Commodities();
    }

    Double getCommodityPrice(String commodity);
}
