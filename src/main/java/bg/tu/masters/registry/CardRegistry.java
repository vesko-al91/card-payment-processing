package bg.tu.masters.registry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.tu.masters.entity.CardEntity;

@Stateless
public class CardRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public CardEntity loadCard(String cardRef) {
        CardEntity cardEntity = null;

        try {
            Query query = em.createNamedQuery(CardEntity.FIND_CARD_BY_CARD_REF);
            query.setParameter("cardRef", cardRef);
            cardEntity = (CardEntity) query.getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return cardEntity;
    }

}
