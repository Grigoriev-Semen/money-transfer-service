package ru.grigoriev.moneytransferservice;


import ru.grigoriev.moneytransferservice.model.Amount;
import ru.grigoriev.moneytransferservice.model.Card;
import ru.grigoriev.moneytransferservice.model.request.ConfirmRequest;
import ru.grigoriev.moneytransferservice.model.request.TransferRequest;
import ru.grigoriev.moneytransferservice.model.response.TransferAndConfirmResponse;

public class DataForTest {

    public static final String CARD_NUM_1 = "1111111111111111";
    public static final String CARD_TILL_1 = "11/25";
    public static final String CARD_CVV_1 = "111";
    public static final Card CARD_1 = new Card(CARD_NUM_1, CARD_TILL_1, CARD_CVV_1, new Amount(100000, "RUR"));

    public static final String CARD_NUM_2 = "2222222222222222";
    public static final String CARD_TILL_2 = "11/26";
    public static final String CARD_CVV_2 = "222";
    public static final Card CARD_2 = new Card(CARD_NUM_2, CARD_TILL_2, CARD_CVV_2, new Amount(200000, "RUR"));

    public static final String OPERATION_ID = "1";
    public static final String CODE = "0000";

    public static final TransferRequest TRANSFER_REQUEST = new TransferRequest(CARD_NUM_1, CARD_TILL_1, CARD_CVV_1, CARD_NUM_2, new Amount(1000, "RUR"));
    public static final TransferAndConfirmResponse TRANSFER_AND_CONFIRM_RESPONSE = new TransferAndConfirmResponse("1");
    public static final ConfirmRequest CONFIRM_REQUEST = new ConfirmRequest(OPERATION_ID, CODE);
}