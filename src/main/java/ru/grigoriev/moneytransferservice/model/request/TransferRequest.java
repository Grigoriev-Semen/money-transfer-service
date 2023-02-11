package ru.grigoriev.moneytransferservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.grigoriev.moneytransferservice.model.Amount;

@Data
@AllArgsConstructor
public class TransferRequest {

    private String cardFromNumber;

    private String cardFromValidTill;

    private String cardFromCVV;

    private String cardToNumber;

    private Amount amount;
}