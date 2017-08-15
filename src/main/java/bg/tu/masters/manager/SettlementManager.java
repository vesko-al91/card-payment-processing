package bg.tu.masters.manager;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.AuthorizationEntity;
import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.SettlementRequestStatus;
import bg.tu.masters.enums.TransactionType;
import bg.tu.masters.registry.AuthorizationRegistry;
import bg.tu.masters.registry.AuthorizationRequestRegistry;
import bg.tu.masters.registry.TransactionRegistry;
import bg.tu.masters.request.SettlementRequest;
import bg.tu.masters.util.ExceptionHandler;

@Stateless
public class SettlementManager {

    @EJB
    private BalanceManager balanceManager;

    @EJB
    private AccountManager accountManager;

    @EJB
    private ExceptionHandler exceptionHandler;

    @EJB
    private CurrencyConversionManager currencyConversionManager;

    @EJB
    private AuthorizationRegistry authorizationRegistry;

    @EJB
    private AuthorizationRequestRegistry authRequestRegistry;

    @EJB
    private TransactionRegistry transactionRegistry;

    public SettlementProcessResult processSettlementRequest(SettlementRequest request) {
        try {
            AuthorizationRequestEntity authRequest = authRequestRegistry.loadAuthorizationRequest(request.getAuthRef());
            AuthorizationEntity authorization = authorizationRegistry.loadAuthorization(authRequest.getId());
            balanceManager.releaseBalance(authorization.getAccountId(), authorization.getAmount());
            createTransaction(request);
            authorizationRegistry.settleAuthorization(authorization);
            return new SettlementProcessResult(SettlementRequestStatus.APPROVED);
        } catch(Exception e) {
            e.printStackTrace();
            return exceptionHandler.handleSettlementException(e);
        }
    }

    private void createTransaction(SettlementRequest request) {
        Long accountId = Long.valueOf(request.getAccountId());
        String accountCurrency = accountManager.getAccountCurrency(accountId);
        BigDecimal amount = null;
        if (accountCurrency.equalsIgnoreCase(request.getTrnCurrency())) {
            amount = new BigDecimal(request.getTrnAmount());
        } else {
            amount = currencyConversionManager.convert(new BigDecimal(request.getTrnAmount()),
                    request.getTrnCurrency(), accountCurrency);
        }

        balanceManager.withdraw(accountId, amount);
        transactionRegistry.createTransaction(accountId, amount, accountCurrency, request.getAuthRef(),
                TransactionType.valueOf(TransactionType.class, request.getTrnType()));
    }

}
