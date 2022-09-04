package com.example.rabbitmqttldlq.listeners;

import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "message-queue-for-retry-1", durable = "true",
                            arguments = {
                                    @Argument(name = "x-dead-letter-exchange", value = "message-exchange-1"),
                                    @Argument(name = "x-dead-letter-routing-key", value = "message-dlq-key-1"),
                                    @Argument(name = "x-message-ttl", value = "60000", type = "java.lang.Integer"),
                                    @Argument(name = "x-max-length", value = "5", type = "java.lang.Integer")
                            }),
                    key = "message-key-4",
                    exchange = @Exchange(name = "message-exchange-1", type = ExchangeTypes.TOPIC)
            ))
    public void listenMessageQueue(Message<String> in) throws Exception {
        System.out.println("----------------------------------");
        System.out.println("Consumer | Message received");
        System.out.println("----------------------------------");
        boolean redelivered = (boolean) in.getHeaders().get("amqp_redelivered");
        System.out.println("REDELIVERED: " + redelivered);
        System.out.println(in);
        System.out.println("----------------------------------");

        throw new ImmediateRequeueAmqpException("Requeuing message");
    }


}
