
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Item {

    @SerializedName("extracted_price")
    private Double extractedPrice;
    @Expose
    private String link;
    @Expose
    private String name;
    @Expose
    private String price;
    @SerializedName("price_movement")
    private PriceMovement priceMovement;
    @SerializedName("serpapi_link")
    private String serpapiLink;
    @Expose
    private String stock;

    public Double getExtractedPrice() {
        return extractedPrice;
    }

    public void setExtractedPrice(Double extractedPrice) {
        this.extractedPrice = extractedPrice;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
