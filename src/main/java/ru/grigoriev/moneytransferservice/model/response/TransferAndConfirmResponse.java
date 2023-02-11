package ru.grigoriev.moneytransferservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferAndConfirmResponse {
    private String operationId;
}