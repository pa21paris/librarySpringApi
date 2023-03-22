/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.firstSpringApp.ErrorHandlers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@ControllerAdvice
public class Verifier {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        Map<String, String> errorMap = new HashMap<>();
        fieldErrors.forEach((err) -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(errorMap);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintsViolated = ex.getConstraintViolations();
        Map<String, String> violationsMap = new HashMap<>();

        constraintsViolated.forEach((constraintViolated) -> {
            String[] propertyPath = constraintViolated.getPropertyPath().toString().split("\\.");
            violationsMap.put(propertyPath[propertyPath.length - 1],
                    constraintViolated.getMessage());
        });
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(violationsMap);
    }
}
