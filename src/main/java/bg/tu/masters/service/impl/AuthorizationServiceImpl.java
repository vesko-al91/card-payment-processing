package bg.tu.masters.service.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.tu.masters.entity.AddressEntity;
import bg.tu.masters.service.AuthorizationService;

@Stateless
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public Response processAuthorizationRequest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myOracle");
        EntityManager em = emf.createEntityManager();

        AddressEntity address = new AddressEntity();
        address.setAddress1("MyAddress 1");
        address.setAddress2("MyAddress 2");
        address.setCity("MyCity");
        address.setCountry("MyCountry");
        em.persist(address);
        em.flush();

        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).build();
    }

}
