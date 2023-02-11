package ru.grigoriev.moneytransferservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.grigoriev.moneytransferservice.model.response.ErrorResponse;

import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class TransferMoneyControllerAdvice {

    private final AtomicInteger id = new AtomicInteger();

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleInputData(TransferException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

    @ExceptionHandler(ConfirmException.class)
    public ResponseEntity<ErrorResponse> handleConfirmation(ConfirmException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }
}