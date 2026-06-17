package ru.effectivemobile.api.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
