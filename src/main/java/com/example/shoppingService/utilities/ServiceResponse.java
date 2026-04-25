package com.example.shoppingService.utilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ServiceResponse<T> {

    private final T entity;
    private final ErrorType error;
    private final String message;

    // === 2 PARAMS CONSTRUCTOR ===
    public ServiceResponse(T entity, ErrorType error) {
        this(entity, error, "");
    }

    // === FACTORY METHODS ===

    public static <T> ServiceResponse<T> success(T entity) {
        return new ServiceResponse<>(entity, null, "");
    }

    public static <T> ServiceResponse<T> success(T entity, String message) {
        return new ServiceResponse<>(entity, null, message);
    }

    public static <T> ServiceResponse<T> success() {
        // For operations that don't return an entity (e.g. delete)
        return new ServiceResponse<>(null, null, "");
    }

    public static <T> ServiceResponse<T> failure(ErrorType error) {
        return new ServiceResponse<>(null, error, "");
    }

    public static <T> ServiceResponse<T> failure(ErrorType error, String message) {
        return new ServiceResponse<>(null, error, message);
    }

    // === STATUS METHODS ===
    public boolean hasError() {
        return error != null;
    }

    public boolean isSuccess() {
        // Success is defined by the absence of error, not the presence of entity
        return error == null;
    }

    // === ERROR TYPES ===

    public enum ErrorType {
        RECORD_INACTIVE,
        OPTIMISTIC_LOCK,
        GENERAL_ERROR,
        RECORD_EXIST,
        CONFIGURATION_ERROR,
        VALIDATION_ERROR,
        EXTERNAL_API_ERROR,
        NOT_FOUND
    }
}
