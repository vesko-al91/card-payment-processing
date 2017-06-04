package bg.tu.masters.registry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.AccountEntity;

@Stateless
public class AccountRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public AccountEntity loadAccount(Long accountId) {
        AccountEntity accountEntity = null;

        try {
            accountEntity = em.find(AccountEntity.class, accountId);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return accountEntity;
    }

}
