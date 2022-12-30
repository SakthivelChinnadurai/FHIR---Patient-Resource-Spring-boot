/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.exception;

/**
 * Creating a custom Exception for user input not found exception.
 *
 * @author Sakthivel
 * @version 1.0
 * @since 28-12-2022
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * passes exception for user input not found.
     *
     * @param message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
