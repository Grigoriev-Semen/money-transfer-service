package ru.grigoriev.moneytransferservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.grigoriev.moneytransferservice.model.Card;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.grigoriev.moneytransferservice.DataForTest.*;

class TransferMoneyRepositoryImplTest {
    TransferRepository repository;

    private final Map<String, Card> testCards = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        repository = new TransferRepositoryImpl();
        testCards.put(CARD_NUM_1, CARD_1);
        testCards.put(CARD_NUM_2, CARD_2);
    }

    @Test
    void getCard() {
        assertEquals( repository.getCard(CARD_NUM_1, ""),testCards.get(CARD_NUM_1));
    }

    @Test
    void getOperationId() {
        assertEquals(repository.getOperationId(), OPERATION_ID);
    }

    @Test
    public void deleteTransfer() {
        repository.addTransfer(OPERATION_ID,TRANSFER_REQUEST,CODE);
        assertEquals(repository.deleteTransfer(OPERATION_ID), TRANSFER_REQUEST);
    }
}