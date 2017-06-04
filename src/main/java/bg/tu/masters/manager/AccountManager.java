package bg.tu.masters.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.AccountEntity;
import bg.tu.masters.registry.AccountRegistry;

@Stateless
public class AccountManager {

    @EJB
    private AccountRegistry accountRegistry;

    public String getAccountCurrency(Long accountId) {
        String currency = null;

        AccountEntity account = accountRegistry.loadAccount(accountId);
        if (account != null) {
            currency = account.getCurrency();
        }

        return currency;
    }

}
