package com.banking.ferhatbank.service.rabbitmq;

import com.banking.ferhatbank.request.MoneyTransferMessageRequest;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue moneyTransferQueue;

    public MessageProducer(RabbitTemplate rabbitTemplate, Queue moneyTransferQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.moneyTransferQueue = moneyTransferQueue;
        // JSON mesaj dönüştürücüyü ayarlayın
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void sendMoneyTransferMessage(MoneyTransferMessageRequest request) {
        rabbitTemplate.convertAndSend(moneyTransferQueue.getName(), request);
    }


}