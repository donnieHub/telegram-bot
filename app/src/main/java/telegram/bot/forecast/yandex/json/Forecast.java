package telegram.bot.forecast.yandex.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

@SuppressWarnings("unused")
public class Forecast {

    @SerializedName("date")
    private String mDate;
    @SerializedName("date_ts")
    private Long mDateTs;
    @SerializedName("moon_code")
    private Long mMoonCode;
    @SerializedName("moon_text")
    private String mMoonText;
    @SerializedName("parts")
    private List<Part> mParts;
    @SerializedName("sunrise")
    private String mSunrise;
    @SerializedName("sunset")
    private String mSunset;
    @SerializedName("week")
    private Long mWeek;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Long getDateTs() {
        return mDateTs;
    }

    public void setDateTs(Long dateTs) {
        mDateTs = dateTs;
    }

    public Long getMoonCode() {
        return mMoonCode;
    }

    public void setMoonCode(Long moonCode) {
        mMoonCode = moonCode;
    }

    public String getMoonText() {
        return mMoonText;
    }

    public void setMoonText(String moonText) {
        mMoonText = moonText;
    }

    public List<Part> getParts() {
        return mParts;
    }

    public void setParts(List<Part> parts) {
        mParts = parts;
    }

    public String getSunrise() {
        return mSunrise;
    }

    public void setSunrise(String sunrise) {
        mSunrise = sunrise;
    }

    public String getSunset() {
        return mSunset;
    }

    public void setSunset(String sunset) {
        mSunset = sunset;
    }

    public Long getWeek() {
        return mWeek;
    }

    public void setWeek(Long week) {
        mWeek = week;
    }

}
