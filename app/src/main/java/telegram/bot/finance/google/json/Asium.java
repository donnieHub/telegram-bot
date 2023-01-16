
package telegram.bot.finance.google.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Asium {

    @Expose
    private String link;
    @Expose
    private String name;
    @Expose
    private Double price;
    @SerializedName("price_movement")
    private PriceMovement priceMovement;
    @SerializedName("serpapi_link")
    private String serpapiLink;
    @Expose
    private String stock;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PriceMovement getPriceMovement() {
        return priceMovement;
    }

    public void setPriceMovement(PriceMovement priceMovement) {
        this.priceMovement = priceMovement;
    }

    public String getSerpapiLink() {
        return serpapiLink;
    }

    public void setSerpapiLink(String serpapiLink) {
        this.serpapiLink = serpapiLink;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
