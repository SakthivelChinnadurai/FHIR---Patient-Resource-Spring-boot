/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.exception;

import java.util.Map;

/**
 * Creating a Error response validation Exception for HTTP error status.
 *
 * @author Sakthivel
 * @version 1.0
 * @since 28-12-2022
 */
public class ErrorResponseValidation {

    private int statusCode;
    private Map<String, String> message;

    public ErrorResponseValidation(int statusCode, Map<String, String> errorMessage) {
        this.message = errorMessage;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
