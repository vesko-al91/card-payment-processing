package bg.tu.masters.response;

import bg.tu.masters.enums.AuthorizationRequestStatus;

public class AuthorizationResponse {

    private AuthorizationRequestStatus response;

    public AuthorizationResponse(AuthorizationRequestStatus response) {
        this.response = response;
    }

    public String getResponse() {
        return response.name();
    }

}
