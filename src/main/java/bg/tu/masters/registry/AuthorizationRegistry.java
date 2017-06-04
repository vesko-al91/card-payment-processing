package bg.tu.masters.registry;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.tu.masters.entity.AuthorizationEntity;
import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.AuthorizationStatus;

@Stateless
public class AuthorizationRegistry {

    @PersistenceContext(unitName="myOracle")
    private EntityManager em;

    public AuthorizationEntity loadAuthorization(Long authorizationId) {
        AuthorizationEntity authorizationEntity = null;

        try {
            authorizationEntity = em.find(AuthorizationEntity.class, authorizationId);
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
        } catch(Exception e) {
            e.printStackTrace();
        }

        return authorizationEntity;
    }

}
