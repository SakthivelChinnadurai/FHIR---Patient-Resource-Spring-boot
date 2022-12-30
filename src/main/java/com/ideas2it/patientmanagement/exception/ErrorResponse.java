/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.exception;

/**
 * Creating a Error response Exception for HTTP error status.
 *
 * @author sakthivel
 * @version 1.0
 * @since 28-12-2022
 */
public class ErrorResponse {

    private int statusCode;
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
