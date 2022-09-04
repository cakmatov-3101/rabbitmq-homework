package com.example.rabbitmqretryfmx.listeners;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "message-queue-with-retry-1", durable = "true"),
                    key = "message-key-with-retry-fmx-1",
                    exchange = @Exchange(name = "message-exchange-1", type = ExchangeTypes.TOPIC)
            ))
    public void listenMessageQueue(Message<String> in) {
        System.out.println(in.getHeaders());
    }
}
