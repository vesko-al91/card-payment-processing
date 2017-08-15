package bg.tu.masters.registry;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.SettlementRequestEntity;
import bg.tu.masters.enums.SettlementRequestStatus;
import bg.tu.masters.request.SettlementRequest;

@Stateless
public class SettlementRequestRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public SettlementRequestEntity storeSettlementRequest(SettlementRequest request, SettlementRequestStatus status) {
        SettlementRequestEntity settlementRequestEntity = new SettlementRequestEntity();
        settlementRequestEntity.setAccountId(request.getAccountId());
        settlementRequestEntity.setCardRef(request.getCardRef());
        settlementRequestEntity.setAuthRef(request.getAuthRef());
        settlementRequestEntity.setCreateTime(new Date());
        settlementRequestEntity.setTrnAmount(request.getTrnAmount());
        settlementRequestEntity.setTrnCurrency(request.getTrnCurrency());
        settlementRequestEntity.setSettlementTime(request.getSettlementTime());
        settlementRequestEntity.setTrnType(request.getTrnType());
        settlementRequestEntity.setStatus(status);

        em.persist(settlementRequestEntity);
        em.flush();

        return settlementRequestEntity;
    }

}
