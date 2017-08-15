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

import bg.tu.masters.enums.SettlementRequestStatus;

@Entity
@Table(name = "SETTLEMENT_REQUEST")
public class SettlementRequestEntity implements Serializable {
    private static final long serialVersionUID = -3931722359942343084L;

    @Id
    @Column(name="ID", nullable=false)
    @SequenceGenerator(name="seq_settl_req", sequenceName="seq_settl_req_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_settl_req")
    private Long id;

    @Column(name = "CARD_REF")
    private String cardRef;

    @Column(name = "AUTH_REF")
    private String authRef;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "TRN_AMOUNT")
    private String trnAmount;

    @Column(name = "TRN_CURRENCY")
    private String trnCurrency;

    @Column(name = "TRN_TYPE")
    private String trnType;

    @Column(name = "SETTLEMENT_TIME")
    private String settlementTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private SettlementRequestStatus status;

    public SettlementRequestEntity() {
        // Default public constructor.
    }

    public Long getId() {
        return id;
    }

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }

    public String getAuthRef() {
        return authRef;
    }

    public void setAuthRef(String authRef) {
        this.authRef = authRef;
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

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SettlementRequestStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementRequestStatus status) {
        this.status = status;
    }

}
