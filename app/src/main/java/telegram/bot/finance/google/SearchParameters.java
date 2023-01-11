
package telegram.bot.finance.google;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SearchParameters {

    @Expose
    private String engine;
    @Expose
    private String hl;
    @Expose
    private String q;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getHl() {
        return hl;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

}
