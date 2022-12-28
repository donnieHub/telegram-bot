package telegram.bot.forecast.YandexApiResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Fact {

    @SerializedName("condition")
    private String mCondition;
    @SerializedName("daytime")
    private String mDaytime;
    @SerializedName("feels_like")
    private Long mFeelsLike;
    @SerializedName("humidity")
    private Long mHumidity;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("obs_time")
    private Long mObsTime;
    @SerializedName("polar")
    private Boolean mPolar;
    @SerializedName("pressure_mm")
    private Long mPressureMm;
    @SerializedName("pressure_pa")
    private Long mPressurePa;
    @SerializedName("season")
    private String mSeason;
    @SerializedName("temp")
    private Long mTemp;
    @SerializedName("temp_water")
    private Long mTempWater;
    @SerializedName("wind_dir")
    private String mWindDir;
    @SerializedName("wind_gust")
    private Double mWindGust;
    @SerializedName("wind_speed")
    private Double mWindSpeed;

    public String getCondition() {
        return mCondition;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getDaytime() {
        return mDaytime;
    }

    public void setDaytime(String daytime) {
        mDaytime = daytime;
    }

    public Long getFeelsLike() {
        return mFeelsLike;
    }

    public void setFeelsLike(Long feelsLike) {
        mFeelsLike = feelsLike;
    }

    public Long getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Long humidity) {
        mHumidity = humidity;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Long getObsTime() {
        return mObsTime;
    }

    public void setObsTime(Long obsTime) {
        mObsTime = obsTime;
    }

    public Boolean getPolar() {
        return mPolar;
    }

    public void setPolar(Boolean polar) {
        mPolar = polar;
    }

    public Long getPressureMm() {
        return mPressureMm;
    }

    public void setPressureMm(Long pressureMm) {
        mPressureMm = pressureMm;
    }

    public Long getPressurePa() {
        return mPressurePa;
    }

    public void setPressurePa(Long pressurePa) {
        mPressurePa = pressurePa;
    }

    public String getSeason() {
        return mSeason;
    }

    public void setSeason(String season) {
        mSeason = season;
    }

    public Long getTemp() {
        return mTemp;
    }

    public void setTemp(Long temp) {
        mTemp = temp;
    }

    public Long getTempWater() {
        return mTempWater;
    }

    public void setTempWater(Long tempWater) {
        mTempWater = tempWater;
    }

    public String getWindDir() {
        return mWindDir;
    }

    public void setWindDir(String windDir) {
        mWindDir = windDir;
    }

    public Double getWindGust() {
        return mWindGust;
    }

    public void setWindGust(Double windGust) {
        mWindGust = windGust;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
    }

}
