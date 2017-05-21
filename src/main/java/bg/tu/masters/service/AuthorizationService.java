package bg.tu.masters.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/auth")
public interface AuthorizationService {

    @GET
    Response processAuthorizationRequest();
}
