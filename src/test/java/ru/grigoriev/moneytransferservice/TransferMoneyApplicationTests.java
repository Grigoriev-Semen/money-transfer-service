package ru.grigoriev.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.grigoriev.moneytransferservice.model.response.TransferAndConfirmResponse;


import java.util.Objects;

import static ru.grigoriev.moneytransferservice.DataForTest.OPERATION_ID;
import static ru.grigoriev.moneytransferservice.DataForTest.TRANSFER_REQUEST;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferMoneyApplicationTests {

    private static final String HOST_WITHOUT_PORT = "http://localhost:";
    private static final String TRANSFER = "/transfer";
    private static final int PORT = 5500;

    @Autowired
    TestRestTemplate testRestTemplate;

    private static final GenericContainer<?> transferContainer = new GenericContainer<>("backend").withExposedPorts(PORT);

    @BeforeAll
    public static void startContainer() {
        transferContainer.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<TransferAndConfirmResponse> forTransfer = testRestTemplate
                .postForEntity(HOST_WITHOUT_PORT + transferContainer.getMappedPort(PORT) + TRANSFER, TRANSFER_REQUEST, TransferAndConfirmResponse.class);
        Assertions.assertEquals(Objects.requireNonNull(forTransfer.getBody()).getOperationId(), OPERATION_ID);
    }
}