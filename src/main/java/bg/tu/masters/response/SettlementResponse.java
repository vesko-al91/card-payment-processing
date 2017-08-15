package bg.tu.masters.response;

import bg.tu.masters.enums.SettlementRequestStatus;

public class SettlementResponse {

    private SettlementRequestStatus response;

    public SettlementResponse(SettlementRequestStatus response) {
        this.response = response;
    }

    public String getResponse() {
        return response.name();
    }

}
