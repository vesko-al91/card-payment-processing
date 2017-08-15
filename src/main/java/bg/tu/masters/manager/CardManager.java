package bg.tu.masters.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.CardEntity;
import bg.tu.masters.enums.CardStatus;
import bg.tu.masters.enums.CardType;
import bg.tu.masters.enums.TransactionType;
import bg.tu.masters.exception.card.CardDoesNotBelongToAccountException;
import bg.tu.masters.exception.card.CardNotActiveException;
import bg.tu.masters.exception.card.SecurityCodeMismatchException;
import bg.tu.masters.exception.card.TransactionNotAllowedToCardException;
import bg.tu.masters.registry.CardRegistry;

@Stateless
public class CardManager {

    @EJB
    private CardRegistry cardRegistry;

    public void doesCardBelongToAccount(String cardRef, Long accountId) throws CardDoesNotBelongToAccountException {
        CardEntity card = cardRegistry.loadCard(cardRef);
        if (card == null || !card.getAccount().getId().equals(accountId)) {
            throw new CardDoesNotBelongToAccountException();
        }
    }

    public void isCardActive(String cardRef) throws CardNotActiveException {
        CardEntity card = cardRegistry.loadCard(cardRef);
        if (card == null || !CardStatus.ACTIVATED.equals(card.getStatus())) {
            throw new CardNotActiveException();
        }
    }

    public void isTransactionAllowedForCard(String cardRef, String trnType, String cardPresent, String clientPresent)
            throws TransactionNotAllowedToCardException {
        CardEntity card = cardRegistry.loadCard(cardRef);
        if (card == null ||
                (CardType.VIRTUAL.equals(card.getType()) && TransactionType.ATM.name().equals(trnType)) ||
                (TransactionType.ATM.name().equals(trnType) && !("1".equals(cardPresent) && "1".equals(clientPresent)))) {
            throw new TransactionNotAllowedToCardException();
        }
    }

    public void checkSecurityCode(String cardRef, String code, String cardPresent) throws SecurityCodeMismatchException {
        CardEntity card = cardRegistry.loadCard(cardRef);
        if (card == null) {
            throw new SecurityCodeMismatchException();
        }
        String codeToMatch = "1".equals(cardPresent) ? card.getPinCode() : card.getCvvCode();
        if (!codeToMatch.equalsIgnoreCase(code)) {
            throw new SecurityCodeMismatchException();
        }
    }

}
