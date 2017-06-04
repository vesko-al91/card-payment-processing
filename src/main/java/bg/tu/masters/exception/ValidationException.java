package bg.tu.masters.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = 7354167844918963680L;

    private String field;
    private String comment;

    public ValidationException(String field, String comment) {
        this.field = field;
        this.comment = comment;
    }

    public String getField() {
        return field;
    }

    public String getComment() {
        return comment;
    }

}
