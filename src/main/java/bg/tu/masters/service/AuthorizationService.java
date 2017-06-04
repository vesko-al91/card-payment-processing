package bg.tu.masters.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.tu.masters.request.AuthorizationRequest;
import bg.tu.masters.response.AuthorizationResponse;

@Path("/authorize")
public interface AuthorizationService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AuthorizationResponse processAuthorizationRequest(AuthorizationRequest request);
}
