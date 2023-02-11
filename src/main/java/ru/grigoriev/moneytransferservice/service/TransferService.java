package ru.grigoriev.moneytransferservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.grigoriev.moneytransferservice.exception.TransferException;
import ru.grigoriev.moneytransferservice.model.Card;
import ru.grigoriev.moneytransferservice.model.request.ConfirmRequest;
import ru.grigoriev.moneytransferservice.model.request.TransferRequest;
import ru.grigoriev.moneytransferservice.model.response.TransferAndConfirmResponse;
import ru.grigoriev.moneytransferservice.repository.TransferRepository;


@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

    private final TransferRepository repository;

    public TransferAndConfirmResponse transfer(TransferRequest transferRequest) {

        final Card cardFromNumber = repository.getCard(transferRequest.getCardFromNumber(), "отправителя");
        final Card cardToNumber = repository.getCard(transferRequest.getCardToNumber(), "получателя");

        if (cardFromNumber.getNumber().equals(cardToNumber.getNumber())) {
            log.error("Номера карт идентичны - \"{}\", \"{}\"", cardFromNumber.getNumber(), cardToNumber.getNumber());
            throw new TransferException("Номера карт идентичны");
        }

        validCard(cardFromNumber, transferRequest);

        if (cardFromNumber.getAmount().getValue() < transferRequest.getAmount().getValue()) {
            log.error("Недостаточно средств: карта номер - \"{}\", средства на карте - \"{}\", запрашиваемая сумма - \"{}\""
                    , cardFromNumber.getNumber(), cardFromNumber.getAmount(), transferRequest.getAmount());
            throw new TransferException("Недостаточно средств для перевода.");
        }

        String transferId = repository.getOperationId();
        repository.addTransfer(transferId, transferRequest, "0000");

        return new TransferAndConfirmResponse(transferId);
    }

    public TransferAndConfirmResponse confirm(ConfirmRequest confirmRequest) {
        final String operationId = confirmRequest.getOperationId();
        final TransferRequest transferRequest = repository.deleteTransfer(operationId);

        final Card cardFromNumber = repository.getCard(transferRequest.getCardFromNumber(), "отправителя");
        final Card cardToNumber = repository.getCard(transferRequest.getCardToNumber(), "получателя");

        final int cardFromNumberValue = cardFromNumber.getAmount().getValue();
        final int cardToNumberValue = cardToNumber.getAmount().getValue();
        final int transferRequestValue = transferRequest.getAmount().getValue();
        final int commissionTransfer = (int) (transferRequestValue * 0.01);

        cardFromNumber.getAmount().setValue(cardFromNumberValue - transferRequestValue);
        cardToNumber.getAmount().setValue(cardToNumberValue + transferRequestValue - commissionTransfer);

        log.error("Операция успешна. Номер операции \"{}\". Карта отправителя \"{}\". Карта получателя \"{}\". Сумма перевода \"{}\". Сумма комиссии \"{}\"."
                , operationId, transferRequest.getCardFromNumber(), transferRequest.getCardToNumber(), transferRequestValue, commissionTransfer);

        return new TransferAndConfirmResponse(operationId);
    }

    private void validCard(Card card, TransferRequest transferRequest) {
        if (!card.getValidTill().equals(transferRequest.getCardFromValidTill()) && card.getCvv().equals(transferRequest.getCardFromCVV())) {
            log.error("Дата действия карты \"{}\" недействительна: \"{}\"", card.getNumber(), card.getValidTill());
            throw new TransferException("Дата действия карты недействительна.");
        } else if (card.getValidTill().equals(transferRequest.getCardFromValidTill()) && !card.getCvv().equals(transferRequest.getCardFromCVV())) {
            log.error("Неверный код карты \"{}\" : \"{}\"", card.getNumber(), card.getCvv());
            throw new TransferException("Неверный код карты.");
        }
    }
}