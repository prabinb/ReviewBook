package api.exceptions;


public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorMessage      errorMessage;
    private Object[]          messageParams;

    public ServiceException(ErrorMessage errorMessage, Object[] messageParams) {
        this(errorMessage);
        this.messageParams = messageParams;
    }

    public ServiceException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ServiceException(ErrorMessage errorMessage, Object[] messageParams, Throwable cause) {
        this(errorMessage, cause);
        this.messageParams = messageParams;
    }

    public ServiceException(ErrorMessage errorMessage, Throwable cause) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        StringBuffer messageBuffer = new StringBuffer(errorMessage.toString());
        if (messageParams != null) {
            for (int i = 0; i < messageParams.length; i++) {
                messageBuffer.append(":").append(messageParams[i].toString());
            }
        }
        return messageBuffer.toString();
    }
}
