package bg.tu.masters.registry;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.tu.masters.entity.AuthorizationEntity;
import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.AuthorizationStatus;

@Stateless
public class AuthorizationRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public AuthorizationEntity loadAuthorization(Long authReqId) {
        AuthorizationEntity authorizationEntity = null;

        try {
            Query query = em.createNamedQuery(AuthorizationEntity.FIND_AUTH_BY_REQ_ID);
            query.setParameter("authReqId", authReqId);
            authorizationEntity = (AuthorizationEntity) query.getSingleResult();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return authorizationEntity;
    }

    public AuthorizationEntity settleAuthorization(AuthorizationEntity authorizationEntity) {
        try {
            authorizationEntity.setStatus(AuthorizationStatus.SETTLED);
            em.merge(authorizationEntity);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return authorizationEntity;
    }

    public AuthorizationEntity createAuthorization(AuthorizationRequestEntity request, BigDecimal amount) {
        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setAccountId(Long.valueOf(request.getAccountId()));
        authorizationEntity.setCardReference(request.getCardRef());
        authorizationEntity.setCreateTime(new Date());
        authorizationEntity.setRequest(request);
        authorizationEntity.setStatus(AuthorizationStatus.PENDING);
        authorizationEntity.setAmount(amount);

        try {
            em.persist(authorizationEntity);
            em.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return authorizationEntity;
    }

}
