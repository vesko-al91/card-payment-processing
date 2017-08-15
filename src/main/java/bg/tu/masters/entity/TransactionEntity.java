package bg.tu.masters.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import bg.tu.masters.enums.TransactionStatus;
import bg.tu.masters.enums.TransactionType;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 550888551710252366L;

    @Id
    @Column(name="ID", nullable=false)
    @SequenceGenerator(name="seq_trn", sequenceName="seq_trn_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_trn")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private TransactionType type;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "DETAILS")
    private String details;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TransactionStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    public TransactionEntity() {
        // Default public constructor.
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
