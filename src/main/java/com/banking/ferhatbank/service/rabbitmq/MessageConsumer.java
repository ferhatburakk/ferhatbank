package com.banking.ferhatbank.service.rabbitmq;

import com.banking.ferhatbank.request.MoneyTransferMessageRequest;
import com.banking.ferhatbank.service.ValidationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final ValidationService validationService;

    public MessageConsumer(ValidationService validationService) {
        this.validationService = validationService;
    }

    @RabbitListener(queues = "moneyTransferQueue")
    public void receiveMoneyTransferMessage(MoneyTransferMessageRequest request) {
        // Gelen MoneyTransferMessageRequest nesnesini işleme
        System.err.println("Received money transfer message:");
        System.err.println("Source Account Number: " + request.getSourceAccNo());
        System.err.println("Destination Account Number: " + request.getDestAccNo());
        System.err.println("Amount: " + request.getAmount());
        validationService.validateMoneyTransferMessage(request);

    }

}