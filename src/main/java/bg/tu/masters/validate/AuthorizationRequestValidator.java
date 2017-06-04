package bg.tu.masters.validate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.Stateless;

import bg.tu.masters.exception.ValidationException;
import bg.tu.masters.request.AuthorizationRequest;

@Stateless
public class AuthorizationRequestValidator {

    public void validate(AuthorizationRequest request) throws ValidationException {
        try {
            Long.parseLong(request.getAccountId());
        } catch(NumberFormatException e) {
            throw new ValidationException("accountId", "Must be a valid number.");
        }

        if (!("1".equals(request.getCardPresent()) || "0".equals(request.getCardPresent()))) {
            throw new ValidationException("cardPresent", "Must be 0 or 1.");
        }

        if (request.getCardRef().isEmpty()) {
            throw new ValidationException("cardRef", "Must not be empty.");
        }

        if (!("1".equals(request.getClientPresent()) || "0".equals(request.getClientPresent()))) {
            throw new ValidationException("clientPresent", "Must be 0 or 1.");
        }

        if (request.getSecurityCode().isEmpty()) {
            throw new ValidationException("securityCode", "Must not be empty.");
        }

        try {
            new BigDecimal(request.getTrnAmount());
        } catch(NumberFormatException e) {
            throw new ValidationException("trnAmount", "Must be a valid number.");
        }

        if (request.getTrnCity().isEmpty()) {
            throw new ValidationException("trnCity", "Must not be empty.");
        }

        if (request.getTrnCountry().isEmpty()) {
            throw new ValidationException("trnCountry", "Must not be empty.");
        }

        if (! ("BGN".equals(request.getTrnCurrency())
                || "EUR".equals(request.getTrnCurrency())
                || "GBP".equals(request.getTrnCurrency()))) {
            throw new ValidationException("trnCurrency", "Must be BGN, EUR or GBP.");
        }

        if (request.getTrnDescription().isEmpty()) {
            throw new ValidationException("trnDescription", "Must not be empty.");
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            format.parse(request.getTrnLocalTime());
        } catch(ParseException pe) {
            throw new ValidationException("trnLocalTime", "Must be a valid dd/MM/yyyy HH:mm:ss date.");
        }

        if (!("ATM".equals(request.getTrnType()) || "POS".equals(request.getTrnType()))) {
            throw new ValidationException("trnType", "Must be ATM or POS.");
        }

        if (!"RESERVE".equals(request.getType())) {
            throw new ValidationException("type", "Must be RESERVE.");
        }
    }

}
