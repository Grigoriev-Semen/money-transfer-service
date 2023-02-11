package ru.grigoriev.moneytransferservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmRequest {

    private String operationId;

    private String code;
}