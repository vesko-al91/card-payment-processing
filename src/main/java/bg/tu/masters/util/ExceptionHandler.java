package bg.tu.masters.util;

import javax.ejb.Stateless;

import bg.tu.masters.enums.AuthorizationRequestCode;
import bg.tu.masters.enums.AuthorizationRequestStatus;
import bg.tu.masters.enums.SettlementRequestStatus;
import bg.tu.masters.exception.balance.BalanceNotEnoughException;
import bg.tu.masters.exception.card.CardDoesNotBelongToAccountException;
import bg.tu.masters.exception.card.CardExpiredException;
import bg.tu.masters.exception.card.CardNotActiveException;
import bg.tu.masters.exception.card.SecurityCodeMismatchException;
import bg.tu.masters.exception.card.TransactionNotAllowedToCardException;
import bg.tu.masters.manager.AuthorizationProcessResult;
import bg.tu.masters.manager.SettlementProcessResult;

@Stateless
public class ExceptionHandler {

    public AuthorizationProcessResult handleAuthorizationException(Exception e) {
        if (e instanceof BalanceNotEnoughException) {
            return new AuthorizationProcessResult(AuthorizationRequestStatus.DECLINED,
                    AuthorizationRequestCode.BALANCE_NOT_ENOUGH);
        }
        if (e instanceof CardNotActiveException) {
            return new AuthorizationProcessResult(AuthorizationRequestStatus.DECLINED,
                    AuthorizationRequestCode.CARD_NOT_ACTIVE);
        }
        if (e instanceof CardDoesNotBelongToAccountException) {
            return new AuthorizationProcessResult(AuthorizationRequestStatus.DECLINED,
                    AuthorizationRequestCode.CARD_DOES_NOT_BELONG_TO_ACCOUNT);
        }
        if (e instanceof TransactionNotAllowedToCardException) {
            return new AuthorizationProcessResult(AuthorizationRequestStatus.DECLINED,
                    AuthorizationRequestCode.TRANSACTION_NOT_ALLOWED_TO_CARD);
        }
        if (e instanceof SecurityCodeMismatchException) {
            return new AuthorizationProcessResult(AuthorizationRequestStatus.DECLINED,
                    AuthorizationRequestCode.SECURITY_CODE_MISMATCH);
        }

        return new AuthorizationProcessResult(AuthorizationRequestStatus.ERROR, AuthorizationRequestCode.UNKNOWN_ERROR);
    }

    public SettlementProcessResult handleSettlementException(Exception e) {
        return new SettlementProcessResult(SettlementRequestStatus.ERROR);
    }
}
