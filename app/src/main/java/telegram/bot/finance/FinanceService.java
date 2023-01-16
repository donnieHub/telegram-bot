package telegram.bot.finance;

public interface FinanceService {

    static FinanceService getInstance() {
        return new FinanceGoogle();
    }

    Double getDollarExchangeRate();

}
