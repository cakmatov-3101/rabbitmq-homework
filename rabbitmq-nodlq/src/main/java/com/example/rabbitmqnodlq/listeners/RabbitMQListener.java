package com.example.rabbitmqnodlq.listeners;

import org.springframework.amqp.ImmediateRequeueAmqpException;
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
                    value = @Queue(name = "message-queue-1", durable = "true"),
                    key = "message-key-1",
                    exchange = @Exchange(name = "message-exchange-1", type = ExchangeTypes.TOPIC)
            ))
    public void listenMessageQueue(Message<String> in) throws Exception {
        boolean redelivered = (boolean) in.getHeaders().get("amqp_redelivered");
        System.out.println("REDELIVERED: " + redelivered);
        // Just making sure consumer is responsible for redelivery
		if (!redelivered) {
			throw new ImmediateRequeueAmqpException("Testing exceptions");
		}
        System.out.println(in);
    }
}
