
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class About {

    @Expose
    private Description description;
    @Expose
    private String title;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
