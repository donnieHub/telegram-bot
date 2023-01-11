
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SearchMetadata {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("google_finance_url")
    private String googleFinanceUrl;
    @Expose
    private String id;
    @SerializedName("json_endpoint")
    private String jsonEndpoint;
    @SerializedName("processed_at")
    private String processedAt;
    @SerializedName("raw_html_file")
    private String rawHtmlFile;
    @Expose
    private String status;
    @SerializedName("total_time_taken")
    private Double totalTimeTaken;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getGoogleFinanceUrl() {
        return googleFinanceUrl;
    }

    public void setGoogleFinanceUrl(String googleFinanceUrl) {
        this.googleFinanceUrl = googleFinanceUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonEndpoint() {
        return jsonEndpoint;
    }

    public void setJsonEndpoint(String jsonEndpoint) {
        this.jsonEndpoint = jsonEndpoint;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public String getRawHtmlFile() {
        return rawHtmlFile;
    }

    public void setRawHtmlFile(String rawHtmlFile) {
        this.rawHtmlFile = rawHtmlFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(Double totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

}
