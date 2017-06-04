package bg.tu.masters.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class CurrencyConversionManager {

    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        Map<String, BigDecimal> bgnRates = new HashMap<String, BigDecimal>();
        bgnRates.put("EUR", new BigDecimal("1.95900"));
        bgnRates.put("GBP", new BigDecimal("2.25570"));
        Map<String, BigDecimal> eurRates = new HashMap<String, BigDecimal>();
        bgnRates.put("BGN", new BigDecimal("0.50831"));
        bgnRates.put("GBP", new BigDecimal("0.87523"));
        Map<String, BigDecimal> gbpRates = new HashMap<String, BigDecimal>();
        bgnRates.put("EUR", new BigDecimal("1.14255"));
        bgnRates.put("BGN", new BigDecimal("2.24543"));

        Map<String, Map<String, BigDecimal>> fxRates = new HashMap<String, Map<String, BigDecimal>>();
        fxRates.put("BGN", bgnRates);
        fxRates.put("EUR", eurRates);
        fxRates.put("GBP", gbpRates);

        BigDecimal rate = fxRates.get(fromCurrency).get(toCurrency);
        return amount.multiply(rate);
    }

}
