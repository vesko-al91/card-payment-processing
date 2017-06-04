package bg.tu.masters.registry;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.AuthorizationRequestStatus;
import bg.tu.masters.manager.AuthorizationProcessResult;
import bg.tu.masters.request.AuthorizationRequest;

@Stateless
public class AuthorizationRequestRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public AuthorizationRequestEntity loadAuthorizationRequest(Long requestId) {
        AuthorizationRequestEntity authRequestEntity = null;

        try {
            authRequestEntity = em.find(AuthorizationRequestEntity.class, requestId);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return authRequestEntity;
    }

    public void updateAuthorizationRequest(AuthorizationRequestEntity authRequestEntity, AuthorizationProcessResult processResult) {
        if (processResult == null) {
            authRequestEntity.setStatus(AuthorizationRequestStatus.ERROR);
            authRequestEntity.setComments("An internal error has occured.");
        } else {
            authRequestEntity.setStatus(processResult.getStatus());
            authRequestEntity.setComments(processResult.getCode().getCode() + ": " + processResult.getCode().getComments());
        }
        em.merge(authRequestEntity);
    }

    public AuthorizationRequestEntity storeAuthorizationRequest(AuthorizationRequest request) {
        AuthorizationRequestEntity authRequestEntity = new AuthorizationRequestEntity();
        authRequestEntity.setAccountId(request.getAccountId());
        authRequestEntity.setCardPresent(request.getCardPresent());
        authRequestEntity.setCardRef(request.getCardRef());
        authRequestEntity.setClientPresent(request.getClientPresent());
        authRequestEntity.setCreateTime(new Date());
        authRequestEntity.setSecurityCode(request.getSecurityCode());
        authRequestEntity.setTrnAmount(request.getTrnAmount());
        authRequestEntity.setTrnCity(request.getTrnCity());
        authRequestEntity.setTrnCountry(request.getTrnCountry());
        authRequestEntity.setTrnCurrency(request.getTrnCurrency());
        authRequestEntity.setTrnDescription(request.getTrnDescription());
        authRequestEntity.setTrnLocalTime(request.getTrnLocalTime());
        authRequestEntity.setTrnType(request.getTrnType());
        authRequestEntity.setType(request.getType());

        em.persist(authRequestEntity);
        em.flush();

        return authRequestEntity;
    }

}
