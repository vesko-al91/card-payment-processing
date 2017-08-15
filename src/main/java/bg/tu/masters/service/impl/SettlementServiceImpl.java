package bg.tu.masters.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bg.tu.masters.enums.SettlementRequestStatus;
import bg.tu.masters.exception.ValidationException;
import bg.tu.masters.manager.SettlementManager;
import bg.tu.masters.registry.SettlementRequestRegistry;
import bg.tu.masters.request.SettlementRequest;
import bg.tu.masters.response.SettlementResponse;
import bg.tu.masters.service.SettlementService;
import bg.tu.masters.validate.SettlementRequestValidator;

@Stateless
public class SettlementServiceImpl implements SettlementService {

    @EJB
    SettlementRequestValidator settlementValidator;

    @EJB
    SettlementRequestRegistry settlementRegistry;

    @EJB
    SettlementManager settlementManager;

    @Override
    public SettlementResponse processSettlementRequest(SettlementRequest request) {
        SettlementRequestStatus settlementStatus = null;

        try {
            settlementValidator.validate(request);
            settlementStatus = settlementManager.processSettlementRequest(request).getStatus();
        } catch (ValidationException ve) {
            settlementStatus = SettlementRequestStatus.ERROR;
            System.out.println("--------- Settlement validation exception occured: " + ve.getField() + " " + ve.getComment());
        } catch (Exception e) {
            settlementStatus = SettlementRequestStatus.ERROR;
            System.out.println("--------- Settlement validation exception occured: " + e.getMessage());
        } finally {
            settlementRegistry.storeSettlementRequest(request, settlementStatus);
        }

        return new SettlementResponse(settlementStatus);
    }

}
