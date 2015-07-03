package api.results;

import java.io.Serializable;

import api.exceptions.ErrorMessage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class AbstractAPIResult implements Result, Serializable {
    protected Status       status  = null;
    protected String       message = null;
    protected ErrorMessage error   = null;

    protected AbstractAPIResult() {
        //
    }

    protected AbstractAPIResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    protected AbstractAPIResult(ErrorMessage error) {
        this.status = Status.FAILURE;
        this.error = error;
    }

    protected AbstractAPIResult(String message) {
        this.message = message;
    }

    protected AbstractAPIResult(String message, ErrorMessage error) {
        this.status = Status.FAILURE;
        this.message = message;
        this.error = error;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
