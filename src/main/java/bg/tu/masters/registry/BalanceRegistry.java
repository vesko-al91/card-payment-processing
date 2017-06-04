package bg.tu.masters.registry;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.BalanceEntity;

@Stateless
public class BalanceRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public BalanceEntity loadBalance(Long accountId) {
        BalanceEntity balanceEntity = null;

        try {
            balanceEntity = em.find(BalanceEntity.class, accountId);
            if (balanceEntity == null) {
                balanceEntity = new BalanceEntity(accountId, BigDecimal.ZERO, BigDecimal.ZERO);
                em.persist(balanceEntity);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return balanceEntity;
    }

    public void reserveBalance(Long accountId, BigDecimal amount) {
        BalanceEntity balance = null;

        try {
            balance = loadBalance(accountId);
            BigDecimal newAvailableBalance = balance.getAvailableBalance().subtract(amount);
            balance.setAvailableBalance(newAvailableBalance);
            em.merge(balance);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
