
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PriceMovement {

    @Expose
    private String movement;
    @Expose
    private Double percentage;
    @Expose
    private Double value;

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
