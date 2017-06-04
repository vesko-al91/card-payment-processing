package bg.tu.masters.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.entity.AuthorizationRequestEntity;
import bg.tu.masters.enums.AuthorizationRequestStatus;
import bg.tu.masters.exception.ValidationException;
import bg.tu.masters.manager.AuthorizationManager;
import bg.tu.masters.manager.AuthorizationProcessResult;
import bg.tu.masters.registry.AuthorizationRequestRegistry;
import bg.tu.masters.request.AuthorizationRequest;
import bg.tu.masters.response.AuthorizationResponse;
import bg.tu.masters.service.AuthorizationService;
import bg.tu.masters.validate.AuthorizationRequestValidator;

@Stateless
public class AuthorizationServiceImpl implements AuthorizationService {

    @EJB
    AuthorizationRequestRegistry authRequestRegistry;

    @EJB
    AuthorizationRequestValidator authRequestValidator;

    @EJB
    AuthorizationManager authorizationManager;

    @Override
    public AuthorizationResponse processAuthorizationRequest(AuthorizationRequest request) {
        AuthorizationResponse response = null;
        AuthorizationProcessResult processResult = null;
        AuthorizationRequestEntity requestEntity = null;

        try {
            requestEntity = authRequestRegistry.storeAuthorizationRequest(request);
            authRequestValidator.validate(request);
            processResult = authorizationManager.processAuthorizationRequest(requestEntity);
            response = new AuthorizationResponse(processResult.getStatus());
        } catch (ValidationException e) {
            System.out.println("--------- Validation exception occured: " + e.getField() + " " + e.getComment());
            response = new AuthorizationResponse(AuthorizationRequestStatus.ERROR);
        } finally {
            authRequestRegistry.updateAuthorizationRequest(requestEntity, processResult);
        }

        return response;
    }

}
