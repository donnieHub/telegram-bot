
package telegram.bot.commodities.commodities.json;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Rates {

    @SerializedName("BRENTOIL")
    private Double mBRENTOIL;
    @SerializedName("USD")
    private Long mUSD;

    public Double getBRENTOIL() {
        return mBRENTOIL;
    }

    public void setBRENTOIL(Double bRENTOIL) {
        mBRENTOIL = bRENTOIL;
    }

    public Long getUSD() {
        return mUSD;
    }

    public void setUSD(Long uSD) {
        mUSD = uSD;
    }

}
