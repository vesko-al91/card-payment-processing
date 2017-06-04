package bg.tu.masters.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bg.tu.masters.enums.AuthorizationRequestStatus;

@Entity
@Table(name = "AUTH_REQUEST")
public class AuthorizationRequestEntity implements Serializable {
    private static final long serialVersionUID = 5490511010729153131L;

    @Id
    @Column(name="ID", nullable=false)
    @SequenceGenerator(name="seq_auth_req", sequenceName="seq_auth_req_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="seq_auth_req")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CARD_REF")
    private String cardRef;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "TRN_AMOUNT")
    private String trnAmount;

    @Column(name = "TRN_CURRENCY")
    private String trnCurrency;

    @Column(name = "TRN_TYPE")
    private String trnType;

    @Column(name = "TRN_COUNTRY")
    private String trnCountry;

    @Column(name = "TRN_CITY")
    private String trnCity;

    @Column(name = "TRN_DESCRIPTION")
    private String trnDescription;

    @Column(name = "TRN_LOCAL_TIME")
    private String trnLocalTime;

    @Column(name = "CARD_PRESENT")
    private String cardPresent;

    @Column(name = "CLIENT_PRESENT")
    private String clientPresent;

    @Column(name = "SECURITY_CODE")
    private String securityCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AuthorizationRequestStatus status;

    @Column(name = "COMMENTS")
    private String comments;

    public AuthorizationRequestEntity() {
        // Default public constructor.
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTrnAmount() {
        return trnAmount;
    }

    public void setTrnAmount(String trnAmount) {
        this.trnAmount = trnAmount;
    }

    public String getTrnCurrency() {
        return trnCurrency;
    }

    public void setTrnCurrency(String trnCurrency) {
        this.trnCurrency = trnCurrency;
    }

    public String getTrnType() {
        return trnType;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType;
    }

    public String getTrnCountry() {
        return trnCountry;
    }

    public void setTrnCountry(String trnCountry) {
        this.trnCountry = trnCountry;
    }

    public String getTrnCity() {
        return trnCity;
    }

    public void setTrnCity(String trnCity) {
        this.trnCity = trnCity;
    }

    public String getTrnDescription() {
        return trnDescription;
    }

    public void setTrnDescription(String trnDescription) {
        this.trnDescription = trnDescription;
    }

    public String getTrnLocalTime() {
        return trnLocalTime;
    }

    public void setTrnLocalTime(String trnLocalTime) {
        this.trnLocalTime = trnLocalTime;
    }

    public String getCardPresent() {
        return cardPresent;
    }

    public void setCardPresent(String cardPresent) {
        this.cardPresent = cardPresent;
    }

    public String getClientPresent() {
        return clientPresent;
    }

    public void setClientPresent(String clientPresent) {
        this.clientPresent = clientPresent;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public AuthorizationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(AuthorizationRequestStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
