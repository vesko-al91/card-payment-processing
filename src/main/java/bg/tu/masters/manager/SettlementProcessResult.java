package bg.tu.masters.manager;

import bg.tu.masters.enums.SettlementRequestStatus;

public class SettlementProcessResult {

    private SettlementRequestStatus status;

    public SettlementProcessResult(SettlementRequestStatus status) {
        this.status = status;
    }

    public SettlementRequestStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementRequestStatus status) {
        this.status = status;
    }

}
