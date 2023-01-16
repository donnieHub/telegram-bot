
package telegram.bot.commodities.commodities.json;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Data {

    @SerializedName("base")
    private String mBase;
    @SerializedName("date")
    private String mDate;
    @SerializedName("rates")
    private Rates mRates;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("timestamp")
    private Long mTimestamp;
    @SerializedName("unit")
    private String mUnit;

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        mBase = base;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Rates getRates() {
        return mRates;
    }

    public void setRates(Rates rates) {
        mRates = rates;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Long timestamp) {
        mTimestamp = timestamp;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
