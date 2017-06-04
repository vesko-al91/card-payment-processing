package bg.tu.masters.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class CurrencyConversionManager {

    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        Map<String, BigDecimal> bgnRates = new HashMap<String, BigDecimal>();
        bgnRates.put("BGN", BigDecimal.ONE);
        bgnRates.put("EUR", new BigDecimal("0.50854"));
        bgnRates.put("GBP", new BigDecimal("0.44608"));
        Map<String, BigDecimal> eurRates = new HashMap<String, BigDecimal>();
        eurRates.put("EUR", BigDecimal.ONE);
        eurRates.put("BGN", new BigDecimal("1.96642"));
        eurRates.put("GBP", new BigDecimal("0.87728"));
        Map<String, BigDecimal> gbpRates = new HashMap<String, BigDecimal>();
        gbpRates.put("GBP", BigDecimal.ONE);
        gbpRates.put("EUR", new BigDecimal("1.13988"));
        gbpRates.put("BGN", new BigDecimal("2.24149"));

        Map<String, Map<String, BigDecimal>> fxRates = new HashMap<String, Map<String, BigDecimal>>();
        fxRates.put("BGN", bgnRates);
        fxRates.put("EUR", eurRates);
        fxRates.put("GBP", gbpRates);

        BigDecimal rate = fxRates.get(fromCurrency).get(toCurrency);
        return amount.multiply(rate);
    }

}
