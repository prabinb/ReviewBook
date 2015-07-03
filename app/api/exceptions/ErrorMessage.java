package api.exceptions;

import java.io.Serializable;

public final class ErrorMessage implements Serializable {

    private String    message;
    private int       code;
    private ErrorCode errorCode;
    private String    redirect;

    public ErrorMessage(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
    }

    public ErrorMessage(String errorMessage, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.message = errorMessage;
    }

    public ErrorMessage(String errorMessage, ErrorCode errorCode, String redirect) {
        this.errorCode = errorCode;
        this.code = errorCode.getErrorCode();
        this.message = errorMessage;
        this.redirect = redirect;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getRedirect() {
        return redirect;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this).add("message", this.message).add("code", this.errorCode).add("redirect", this.redirect).toString();
    }
}
