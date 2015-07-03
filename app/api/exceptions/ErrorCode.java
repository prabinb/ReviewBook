package api.exceptions;

public enum ErrorCode {

    // User Profile related errors - 600 series
    INVALID_USER_EMAIL_ADDRESS(601), USER_CREATION_ERROR(602), DUPLICATE_USER_EMAIL(603), USER_NOT_FOUND(604), UPDATE_PROFILE_FAILED(605), UPDATE_PROFILE_STATUS_FAILED(606), ENABLE_USERS_FAILED(
            610), DISABLE_USERS_FAILED(611), USER_LIST_ERROR(612), UPDATE_PASSWORD_FAILED(613);

    private final int errorCode;

    private ErrorCode(final int code) {
        errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return String.valueOf(errorCode);
    }
}
