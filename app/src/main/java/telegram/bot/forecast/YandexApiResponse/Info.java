package telegram.bot.forecast.YandexApiResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Info {

    @SerializedName("lat")
    private Double mLat;
    @SerializedName("lon")
    private Double mLon;
    @SerializedName("url")
    private String mUrl;

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
