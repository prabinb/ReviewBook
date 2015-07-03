/*
 * Any use of the Material is governed by the terms of the actual license
 * agreement between LeanTaaS Inc. and the user.
 * Copyright 2014 LeanTaaS Inc., Sunnyvale CA USA.
 * All rights reserved. Any rights not expressly granted herein are reserved.
 */

package api.results;

import api.exceptions.ErrorMessage;

public class APIResult extends AbstractAPIResult {
    private Object data = null;

    private APIResult() {
        //
    }

    public APIResult(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public APIResult(String message) {
        super(message);
    }

    public APIResult(String message, ErrorMessage errorMessage) {
        super(message, errorMessage);
    }

    public APIResult(Status status, String message) {
        super(status, message);
    }

    public APIResult(Status status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public APIResult(Object data) {
        this.status = Status.SUCCESS;
        this.data = data;
    }

    public APIResult(String message, Object data) {
        super(message);
        this.status = Status.SUCCESS;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
