package br.com.finalcraft.im2.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    GENERIC(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    INVALID_FORMULARY_FORM(HttpStatus.BAD_REQUEST, "Invalid Cart Error"),

    //Formulary Codes
    FORMULARY_NOT_FOUND(HttpStatus.BAD_REQUEST),

    //Camunda
    CAMUNDA_GENERIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    ;

    private final HttpStatus defaultStatus;
    private final String defaultMessage;

    ErrorCode(HttpStatus defaultStatus, String defaultMessage) {
        this.defaultStatus = defaultStatus;
        this.defaultMessage = defaultMessage;
    }

    ErrorCode(HttpStatus defaultStatus) {
        this.defaultStatus = defaultStatus;
        this.defaultMessage = this.name();
    }

    public HttpStatus getDefaultStatus() {
        return defaultStatus;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public ErrorResponse newError(){
        return ErrorResponse.of(this);
    }
}
