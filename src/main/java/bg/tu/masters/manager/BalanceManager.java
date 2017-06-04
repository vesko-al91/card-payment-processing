package bg.tu.masters.manager;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.BalanceEntity;
import bg.tu.masters.exception.balance.BalanceNotEnoughException;
import bg.tu.masters.registry.BalanceRegistry;

@Stateless
public class BalanceManager {

    @EJB
    private BalanceRegistry balanceRegistry;

    @EJB
    private AccountManager accountManager;

    @EJB
    private CurrencyConversionManager currencyConversionManager;

    public void checkAvailableBalance(Long accountId, BigDecimal trnAmount, String trnCurrency) throws BalanceNotEnoughException {
        String accountCurrency = accountManager.getAccountCurrency(accountId);
        BigDecimal amount = null;
        if (trnCurrency.equalsIgnoreCase(accountCurrency)) {
            amount = trnAmount;
        } else {
            amount = currencyConversionManager.convert(trnAmount, trnCurrency, accountCurrency);
        }
        BalanceEntity balance = balanceRegistry.loadBalance(accountId);

        if (balance == null || balance.getAvailableBalance().compareTo(amount) < 0) {
            throw new BalanceNotEnoughException();
        }
    }

    public void reserveBalance(Long accountId, BigDecimal amount) {
        balanceRegistry.reserveBalance(accountId, amount);
    }

}
