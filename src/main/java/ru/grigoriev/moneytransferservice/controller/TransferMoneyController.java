package ru.grigoriev.moneytransferservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.grigoriev.moneytransferservice.model.request.ConfirmRequest;
import ru.grigoriev.moneytransferservice.model.request.TransferRequest;
import ru.grigoriev.moneytransferservice.model.response.TransferAndConfirmResponse;
import ru.grigoriev.moneytransferservice.service.TransferService;

@RestController
@AllArgsConstructor
public class TransferMoneyController {

    private TransferService service;

    @PostMapping("/transfer")
    public TransferAndConfirmResponse transfer(@RequestBody TransferRequest transferRequest) {
        return service.transfer(transferRequest);
    }

    @PostMapping("/confirmOperation")
    public TransferAndConfirmResponse confirm(@RequestBody ConfirmRequest confirmRequest) {
        return service.confirm(confirmRequest);
    }
}