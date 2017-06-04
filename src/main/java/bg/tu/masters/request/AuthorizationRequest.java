package bg.tu.masters.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationRequest {

    @XmlElement
    private String type;

    @XmlElement
    private String cardRef;

    @XmlElement
    private String accountId;

    @XmlElement
    private String trnAmount;

    @XmlElement
    private String trnCurrency;

    @XmlElement
    private String trnType;

    @XmlElement
    private String trnCountry;

    @XmlElement
    private String trnCity;

    @XmlElement
    private String trnDescription;

    @XmlElement
    private String trnLocalTime;

    @XmlElement
    private String cardPresent;

    @XmlElement
    private String clientPresent;

    @XmlElement
    private String securityCode;

    public AuthorizationRequest() {
        // Default public constructor.
    }

    public String getType() {
        return type;
    }

    public String getCardRef() {
        return cardRef;
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

    public String getTrnCountry() {
        return trnCountry;
    }

    public String getTrnCity() {
        return trnCity;
    }

    public String getTrnDescription() {
        return trnDescription;
    }

    public String getTrnLocalTime() {
        return trnLocalTime;
    }

    public String getCardPresent() {
        return cardPresent;
    }

    public String getClientPresent() {
        return clientPresent;
    }

    public String getSecurityCode() {
        return securityCode;
    }

}
