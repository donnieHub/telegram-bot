
package telegram.bot.finance.google.json;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class KnowledgeGraph {

    @Expose
    private List<About> about;
    @SerializedName("key_stats")
    private KeyStats keyStats;

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public KeyStats getKeyStats() {
        return keyStats;
    }

    public void setKeyStats(KeyStats keyStats) {
        this.keyStats = keyStats;
    }

}
