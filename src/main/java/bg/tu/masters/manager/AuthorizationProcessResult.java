package bg.tu.masters.manager;

import bg.tu.masters.enums.AuthorizationRequestCode;
import bg.tu.masters.enums.AuthorizationRequestStatus;

public class AuthorizationProcessResult {

    private AuthorizationRequestStatus status;
    private AuthorizationRequestCode code;

    public AuthorizationProcessResult(AuthorizationRequestStatus status, AuthorizationRequestCode code) {
        this.status = status;
        this.code = code;
    }

    public AuthorizationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(AuthorizationRequestStatus status) {
        this.status = status;
    }

    public AuthorizationRequestCode getCode() {
        return code;
    }

    public void setCode(AuthorizationRequestCode code) {
        this.code = code;
    }

}
