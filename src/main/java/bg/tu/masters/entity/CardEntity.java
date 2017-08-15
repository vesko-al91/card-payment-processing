package bg.tu.masters.entity;

import java.io.Serializable;
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

import bg.tu.masters.enums.CardStatus;
import bg.tu.masters.enums.CardType;

@Entity
@Table(name = "CARD")
@NamedQueries({
    @NamedQuery(
            name = CardEntity.FIND_CARD_BY_CARD_REF,
            query = "SELECT c FROM CardEntity c WHERE c.cardRef = :cardRef"
    )
})
public class CardEntity implements Serializable {
    private static final long serialVersionUID = 550888551710252366L;

    public static final String FIND_CARD_BY_CARD_REF = "findCardByCardRef";

    @Id
    @Column(name="ID", nullable=false)
    @SequenceGenerator(name="seq_card", sequenceName="seq_card_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_card")
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "CARD_REF")
    private String cardRef;

    @Column(name = "EXP_MONTH")
    private Integer expiryMonth;

    @Column(name = "EXP_YEAR")
    private Integer expiryYear;

    @Column(name = "CVV")
    private String cvvCode;

    @Column(name = "PIN")
    private String pinCode;

    public CardEntity() {
        // Default public constructor.
    }

    public Long getId() {
        return id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCardRef() {
        return cardRef;
    }

    public void setCardRef(String cardRef) {
        this.cardRef = cardRef;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

}
