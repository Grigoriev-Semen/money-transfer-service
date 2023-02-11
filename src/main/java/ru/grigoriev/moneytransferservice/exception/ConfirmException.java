package ru.grigoriev.moneytransferservice.exception;

public class ConfirmException extends RuntimeException {

    public ConfirmException(String message) {
        super(message);
    }
}