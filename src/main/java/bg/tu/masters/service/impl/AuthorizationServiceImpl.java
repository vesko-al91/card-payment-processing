package bg.tu.masters.service.impl;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.tu.masters.service.AuthorizationService;

@Stateless
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public Response processAuthorizationRequest() {
        return Response.status(Response.Status.OK).entity("OK").type(MediaType.TEXT_PLAIN).build();
    }

}
