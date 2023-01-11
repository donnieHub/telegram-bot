
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Description {

    @Expose
    private String link;
    @SerializedName("link_text")
    private String linkText;
    @Expose
    private String snippet;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}
