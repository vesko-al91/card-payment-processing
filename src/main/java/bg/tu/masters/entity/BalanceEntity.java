package bg.tu.masters.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BALANCE")
public class BalanceEntity implements Serializable {
    private static final long serialVersionUID = -5561315426934538089L;

    @Id
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "TOTAL")
    private BigDecimal totalBalance;

    @Column(name = "AVAILABLE")
    private BigDecimal availableBalance;

    public BalanceEntity() {
        // Default public constructor.
    }

    public BalanceEntity(Long accountId, BigDecimal totalBalance, BigDecimal availableBalance) {
        this.accountId = accountId;
        this.totalBalance = totalBalance;
        this.availableBalance = availableBalance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

}
