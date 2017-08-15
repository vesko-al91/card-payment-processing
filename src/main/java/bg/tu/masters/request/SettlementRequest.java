package bg.tu.masters.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettlementRequest {

    @XmlElement
    private String cardRef;

    @XmlElement
    private String authRef;

    @XmlElement
    private String accountId;

    @XmlElement
    private String trnAmount;

    @XmlElement
    private String trnCurrency;

    @XmlElement
    private String trnType;

    @XmlElement
    private String settlementTime;

    public SettlementRequest() {
        // Default public constructor.
    }

    public String getCardRef() {
        return cardRef;
    }

    public String getAuthRef() {
        return authRef;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTrnAmount() {
        return trnAmount;
    }

    public String getTrnCurrency() {
        return trnCurrency;
    }

    public String getTrnType() {
        return trnType;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

}
