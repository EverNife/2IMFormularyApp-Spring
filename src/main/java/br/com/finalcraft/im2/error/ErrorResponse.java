package br.com.finalcraft.im2.error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.function.Consumer;

public class ErrorResponse extends RuntimeException{

    private final ErrorCode errorCode;
    private HttpStatus httpStatus;
    private String message;
    private HashMap<String, String> extra = new HashMap<>();

    private ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getDefaultStatus();
        this.message = errorCode.getDefaultMessage();
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }

    public ErrorResponse withMessage(String customMessage) {
        this.message = customMessage;
        return this;
    }

    public ErrorResponse withHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ErrorResponse withData(String key, String value) {
        this.extra.put(key, value);
        return this;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorResponse apply(Consumer<ErrorResponse> consumer){
        consumer.accept(this);
        return this;
    }

    @Override
    public String getMessage() {
        return message != null ? message :  errorCode.getDefaultMessage();
    }

    public HashMap<String, String> getExtraData() {
        return extra;
    }
}
