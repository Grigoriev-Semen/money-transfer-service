package ru.grigoriev.moneytransferservice.repository;


import ru.grigoriev.moneytransferservice.model.Card;
import ru.grigoriev.moneytransferservice.model.request.TransferRequest;

public interface TransferRepository {

    Card getCard(String cardNumber, String cardType);

    String getOperationId();

    void addTransfer(String id, TransferRequest transferRequest, String code);

    TransferRequest deleteTransfer(String id);
}