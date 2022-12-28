package telegram.bot.forecast.YandexApiResponse;

import com.google.gson.annotations.SerializedName;

public class YandexWeatherResponse {

    @SerializedName("fact")
    private Fact mFact;
    @SerializedName("forecast")
    private Forecast mForecast;
    @SerializedName("info")
    private Info mInfo;
    @SerializedName("now")
    private Long mNow;
    @SerializedName("now_dt")
    private String mNowDt;

    public Fact getFact() {
        return mFact;
    }

    public void setFact(Fact fact) {
        mFact = fact;
    }

    public Forecast getForecast() {
        return mForecast;
    }

    public void setForecast(Forecast forecast) {
        mForecast = forecast;
    }

    public Info getInfo() {
        return mInfo;
    }

    public void setInfo(Info info) {
        mInfo = info;
    }

    public Long getNow() {
        return mNow;
    }

    public void setNow(Long now) {
        mNow = now;
    }

    public String getNowDt() {
        return mNowDt;
    }

    public void setNowDt(String nowDt) {
        mNowDt = nowDt;
    }

}
