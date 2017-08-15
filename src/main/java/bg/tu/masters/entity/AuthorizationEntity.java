package bg.tu.masters.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bg.tu.masters.enums.AuthorizationStatus;

@Entity
@Table(name = "AUTHORIZATION")
@NamedQueries({
    @NamedQuery(
            name = AuthorizationEntity.FIND_AUTH_BY_REQ_ID,
            query = "SELECT a FROM AuthorizationEntity a WHERE a.request.id = :authReqId"
    )
})
public class AuthorizationEntity implements Serializable {
    private static final long serialVersionUID = 550888551710252366L;

    public static final String FIND_AUTH_BY_REQ_ID = "findAuthByReqId";

    @Id
    @Column(name="ID", nullable=false)
    @SequenceGenerator(name="seq_auth", sequenceName="seq_auth_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_auth")
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "REQUEST_ID")
    private AuthorizationRequestEntity request;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CARD_REF")
    private String cardReference;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AuthorizationStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    public AuthorizationEntity() {
        // Default public constructor.
    }

    public Long getId() {
        return id;
    }

    public AuthorizationRequestEntity getRequest() {
        return request;
    }

    public void setRequest(AuthorizationRequestEntity request) {
        this.request = request;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AuthorizationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthorizationStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
