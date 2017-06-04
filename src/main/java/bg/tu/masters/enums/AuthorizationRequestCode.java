package bg.tu.masters.enums;

public enum AuthorizationRequestCode {
    OK(0, "All checks passed."),
    AMOUNT_LIMIT_EXCEEDED(10, "Amount limit exceeded."),
    VELOCITY_LIMIT_EXCEEDED(11, "Velocity limit exceeded."),
    BALANCE_NOT_ENOUGH(20, "Available balance not enough."),
    CARD_NOT_ACTIVE(30, "Card is not active."),
    CARD_EXPIRED(31, "Card is expired."),
    CARD_DOES_NOT_BELONG_TO_ACCOUNT(40, "Card does not belong to account."),
    TRANSACTION_NOT_ALLOWED_TO_CARD(41, "Transaction not allowed to card."),
    SECURITY_CODE_MISMATCH(42, "Invalid card security code."),
    UNKNOWN_ERROR(99, "Unknown error occured.");

    private Integer code;
    private String comments;

    private AuthorizationRequestCode(Integer code, String comments) {
        this.code = code;
        this.comments = comments;
    }

    public Integer getCode() {
        return code;
    }

    public String getComments() {
        return comments;
    }

}
