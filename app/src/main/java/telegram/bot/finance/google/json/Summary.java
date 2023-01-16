
package telegram.bot.finance.google.json;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Summary {

    @Expose
    private String exchange;
    @Expose
    private List<String> extensions;
    @SerializedName("extracted_price")
    private Double extractedPrice;
    @Expose
    private String price;
    @SerializedName("price_movement")
    private PriceMovement priceMovement;
    @Expose
    private String stock;
    @Expose
    private String title;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public Double getExtractedPrice() {
        return extractedPrice;
    }

    public void setExtractedPrice(Double extractedPrice) {
        this.extractedPrice = extractedPrice;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
