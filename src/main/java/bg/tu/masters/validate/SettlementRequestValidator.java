package bg.tu.masters.validate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.Stateless;

import bg.tu.masters.exception.ValidationException;
import bg.tu.masters.request.SettlementRequest;

@Stateless
public class SettlementRequestValidator {

    public void validate(SettlementRequest request) throws ValidationException {
        try {
            Long.parseLong(request.getAccountId());
        } catch(NumberFormatException e) {
            throw new ValidationException("accountId", "Must be a valid number.");
        }

        if (request.getCardRef().isEmpty()) {
            throw new ValidationException("cardRef", "Must not be empty.");
        }

        if (request.getAuthRef().isEmpty()) {
            throw new ValidationException("authRef", "Must not be empty.");
        }

        try {
            new BigDecimal(request.getTrnAmount());
        } catch(NumberFormatException e) {
            throw new ValidationException("trnAmount", "Must be a valid number.");
        }

        if (! ("BGN".equals(request.getTrnCurrency())
                || "EUR".equals(request.getTrnCurrency())
                || "GBP".equals(request.getTrnCurrency()))) {
            throw new ValidationException("trnCurrency", "Must be BGN, EUR or GBP.");
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            format.parse(request.getSettlementTime());
        } catch(ParseException pe) {
            throw new ValidationException("settlementTime", "Must be a valid dd/MM/yyyy HH:mm:ss date.");
        }

        if (!("ATM".equals(request.getTrnType()) || "POS".equals(request.getTrnType()))) {
            throw new ValidationException("trnType", "Must be ATM or POS.");
        }
    }
}
