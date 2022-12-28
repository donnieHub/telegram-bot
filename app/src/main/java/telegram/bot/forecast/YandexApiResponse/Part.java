package telegram.bot.forecast.YandexApiResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Part {

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
    @SerializedName("part_name")
    private String mPartName;
    @SerializedName("polar")
    private Boolean mPolar;
    @SerializedName("prec_mm")
    private Double mPrecMm;
    @SerializedName("prec_period")
    private Long mPrecPeriod;
    @SerializedName("prec_prob")
    private Long mPrecProb;
    @SerializedName("pressure_mm")
    private Long mPressureMm;
    @SerializedName("pressure_pa")
    private Long mPressurePa;
    @SerializedName("temp_avg")
    private Long mTempAvg;
    @SerializedName("temp_max")
    private Long mTempMax;
    @SerializedName("temp_min")
    private Long mTempMin;
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

    public String getPartName() {
        return mPartName;
    }

    public void setPartName(String partName) {
        mPartName = partName;
    }

    public Boolean getPolar() {
        return mPolar;
    }

    public void setPolar(Boolean polar) {
        mPolar = polar;
    }

    public Double getPrecMm() {
        return mPrecMm;
    }

    public void setPrecMm(Double precMm) {
        mPrecMm = precMm;
    }

    public Long getPrecPeriod() {
        return mPrecPeriod;
    }

    public void setPrecPeriod(Long precPeriod) {
        mPrecPeriod = precPeriod;
    }

    public Long getPrecProb() {
        return mPrecProb;
    }

    public void setPrecProb(Long precProb) {
        mPrecProb = precProb;
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

    public Long getTempAvg() {
        return mTempAvg;
    }

    public void setTempAvg(Long tempAvg) {
        mTempAvg = tempAvg;
    }

    public Long getTempMax() {
        return mTempMax;
    }

    public void setTempMax(Long tempMax) {
        mTempMax = tempMax;
    }

    public Long getTempMin() {
        return mTempMin;
    }

    public void setTempMin(Long tempMin) {
        mTempMin = tempMin;
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
