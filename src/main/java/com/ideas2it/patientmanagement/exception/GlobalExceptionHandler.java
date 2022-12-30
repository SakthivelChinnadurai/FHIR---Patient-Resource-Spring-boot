/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * <p>
 * GlobalExceptionHandler will handle all Http response from server.
 * </p>
 *
 * @author sakthivel
 * @version 1.0
 * @since 28-12-2022
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = DataNotFoundException.class)
    public ErrorResponse handleException(DataNotFoundException dataNotFoundException) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), dataNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponseValidation invalidArgumentHandle(MethodArgumentNotValidException exception) {

        Map<String, String> errorMessage = new HashMap<String, String>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {

            errorMessage.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorResponseValidation(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorResponse inputMisMatch(Exception exception) {
        return new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),"input mismatch");
    }
}
