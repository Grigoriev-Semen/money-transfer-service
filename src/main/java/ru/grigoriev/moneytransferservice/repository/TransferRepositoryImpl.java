package ru.grigoriev.moneytransferservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.grigoriev.moneytransferservice.exception.ConfirmException;
import ru.grigoriev.moneytransferservice.exception.TransferException;
import ru.grigoriev.moneytransferservice.model.Amount;
import ru.grigoriev.moneytransferservice.model.Card;
import ru.grigoriev.moneytransferservice.model.request.TransferRequest;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Slf4j
public class TransferRepositoryImpl implements TransferRepository {

    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<String, TransferRequest> transfersOperation = new ConcurrentHashMap<>();
    private final Map<String, String> codes = new ConcurrentHashMap<>();
    private final AtomicInteger operationId = new AtomicInteger();

    {
        final String cardNum1 = "1111111111111111";
        final String cardNum2 = "2222222222222222";
        cards.put(cardNum1, new Card(cardNum1, "11/25", "111", new Amount(100000, "RUR")));
        cards.put(cardNum2, new Card(cardNum2, "11/26", "222", new Amount(200000, "RUR")));
    }

    @Override
    public Card getCard(String cardNumber, String cardType) {
        return Optional.ofNullable(cards.get(cardNumber))
                .orElseThrow(() -> {
                    log.error("Карта \"{}\" с номером \"{}\" не найдена", cardType, cardNumber);
                    return new TransferException("Карта " + cardType + " с номером " + cardNumber + " не найдена");
                });
    }

    @Override
    public String getOperationId() {
        return Integer.toString(operationId.incrementAndGet());
    }

    @Override
    public void addTransfer(String id, TransferRequest transferRequest, String code) {
        transfersOperation.put(id, transferRequest);
        codes.put(id, code);
    }

    @Override
    public TransferRequest deleteTransfer(String id) {
        Optional.ofNullable(codes.get(id))
                .orElseThrow(() -> {
                    log.error("Операция \"{}\"  не найдена, неверные данные.", id);
                    return new ConfirmException("Операция не найдена, неверные данные.");
                });
        Optional.ofNullable(transfersOperation.get(id))
                .orElseThrow(() -> {
                    log.error("Операция \"{}\"  не найдена, неверные данные.", id);
                    return new ConfirmException("Операция не найдена, неверные данные.");
                });

        codes.remove(id);
        return transfersOperation.remove(id);
    }
}