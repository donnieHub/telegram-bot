
package telegram.bot.finance.google.json;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Tag {

    @Expose
    private String description;
    @Expose
    private String link;
    @SerializedName("serpapi_link")
    private String serpapiLink;
    @Expose
    private String text;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSerpapiLink() {
        return serpapiLink;
    }

    public void setSerpapiLink(String serpapiLink) {
        this.serpapiLink = serpapiLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
