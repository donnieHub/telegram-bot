
package telegram.bot.finance.google;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Markets {

    @Expose
    private List<Asium> asia;
    @Expose
    private List<Crypto> crypto;
    @Expose
    private List<Currency> currencies;
    @Expose
    private List<Europe> europe;
    @Expose
    private List<Future> futures;
    @Expose
    private List<Us> us;

    public List<Asium> getAsia() {
        return asia;
    }

    public void setAsia(List<Asium> asia) {
        this.asia = asia;
    }

    public List<Crypto> getCrypto() {
        return crypto;
    }

    public void setCrypto(List<Crypto> crypto) {
        this.crypto = crypto;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Europe> getEurope() {
        return europe;
    }

    public void setEurope(List<Europe> europe) {
        this.europe = europe;
    }

    public List<Future> getFutures() {
        return futures;
    }

    public void setFutures(List<Future> futures) {
        this.futures = futures;
    }

    public List<Us> getUs() {
        return us;
    }

    public void setUs(List<Us> us) {
        this.us = us;
    }

}
