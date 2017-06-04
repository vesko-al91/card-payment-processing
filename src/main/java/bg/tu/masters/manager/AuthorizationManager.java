package bg.tu.masters.manager;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.AuthorizationRequestCode;
import bg.tu.masters.enums.AuthorizationRequestStatus;
import bg.tu.masters.exception.balance.BalanceNotEnoughException;
import bg.tu.masters.exception.card.CardDoesNotBelongToAccountException;
import bg.tu.masters.exception.card.CardExpiredException;
import bg.tu.masters.exception.card.CardNotActiveException;
import bg.tu.masters.exception.card.SecurityCodeMismatchException;
import bg.tu.masters.exception.card.TransactionNotAllowedToCardException;
import bg.tu.masters.registry.AuthorizationRegistry;
import bg.tu.masters.util.ExceptionHandler;

@Stateless
public class AuthorizationManager {

    @EJB
    private BalanceManager balanceManager;

    @EJB
    private CardManager cardManager;

    @EJB
    private AccountManager accountManager;

    @EJB
    private ExceptionHandler exceptionHandler;

    @EJB
    private CurrencyConversionManager currencyConversionManager;

    @EJB
    private AuthorizationRegistry authorizationRegistry;

    public AuthorizationProcessResult processAuthorizationRequest(AuthorizationRequestEntity request) {
        try {
            performInternalChecks(request);
            createAuthorization(request);
            return new AuthorizationProcessResult(AuthorizationRequestStatus.APPROVED, AuthorizationRequestCode.OK);
        } catch(Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    private void performInternalChecks(AuthorizationRequestEntity request) throws BalanceNotEnoughException,
            CardNotActiveException, CardExpiredException, CardDoesNotBelongToAccountException,
            TransactionNotAllowedToCardException, SecurityCodeMismatchException {
        Long accountId = Long.valueOf(request.getAccountId());
        String cardRef = request.getCardRef();

        balanceManager.checkAvailableBalance(accountId, new BigDecimal(request.getTrnAmount()), request.getTrnCurrency());
        cardManager.isCardActive(cardRef);
        cardManager.isCardExpired(cardRef);
        cardManager.doesCardBelongToAccount(cardRef, accountId);
        cardManager.isTransactionAllowedForCard(cardRef, request.getTrnType());
        cardManager.checkSecurityCode(cardRef, request.getSecurityCode(), Boolean.valueOf(request.getSecurityCode()));
    }

    private void createAuthorization(AuthorizationRequestEntity request) {
        Long accountId = Long.valueOf(request.getAccountId());
        String accountCurrency = accountManager.getAccountCurrency(accountId);
        BigDecimal amount = currencyConversionManager.convert(new BigDecimal(request.getTrnAmount()),
                request.getTrnCurrency(), accountCurrency);

        authorizationRegistry.createAuthorization(request, amount);
        balanceManager.reserveBalance(accountId, amount);
    }

}
