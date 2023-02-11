package ru.grigoriev.moneytransferservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.grigoriev.moneytransferservice.repository.TransferRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.grigoriev.moneytransferservice.DataForTest.*;

@SpringBootTest
class TransferMoneyServiceTest {
    @Mock
    public TransferRepository repository;
    @InjectMocks
    public TransferService service;

    @Test
    void transfer() {
        Mockito.when(repository.getCard(CARD_NUM_1, "отправителя")).thenReturn(CARD_1);
        Mockito.when(repository.getCard(CARD_NUM_2, "получателя")).thenReturn(CARD_2);
        Mockito.when(repository.getOperationId()).thenReturn(OPERATION_ID);
        assertEquals(service.transfer(TRANSFER_REQUEST), TRANSFER_AND_CONFIRM_RESPONSE);
    }

    @Test
    void confirm() {
        Mockito.when(repository.deleteTransfer(OPERATION_ID)).thenReturn(TRANSFER_REQUEST);
        Mockito.when(repository.getCard(CARD_NUM_1, "отправителя")).thenReturn(CARD_1);
        Mockito.when(repository.getCard(CARD_NUM_2, "получателя")).thenReturn(CARD_2);
        Mockito.when(repository.getOperationId()).thenReturn(OPERATION_ID);
        assertEquals(service.confirm(CONFIRM_REQUEST), TRANSFER_AND_CONFIRM_RESPONSE);
    }
}