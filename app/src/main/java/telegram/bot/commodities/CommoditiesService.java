package telegram.bot.commodities;

@Deprecated
public interface CommoditiesService {

    static CommoditiesService getInstance() {
        return new Commodities();
    }

    Double getCommodityPrice(String commodity);
}
