package bg.tu.masters.registry;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.TransactionEntity;
import bg.tu.masters.enums.TransactionStatus;
import bg.tu.masters.enums.TransactionType;

@Stateless
public class TransactionRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public TransactionEntity createTransaction(Long accountId, BigDecimal amount, String accountCurrency, String details, TransactionType type) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setCreateTime(new Date());
        transaction.setCurrency(accountCurrency);
        transaction.setDetails(details);
        transaction.setStatus(TransactionStatus.SETTLED);
        transaction.setType(type);

        em.persist(transaction);
        em.flush();

        return transaction;
    }

}
