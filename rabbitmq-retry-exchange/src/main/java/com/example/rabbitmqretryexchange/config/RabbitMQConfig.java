package com.example.rabbitmqretryexchange.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange topicExchange() {
        return ExchangeBuilder.topicExchange("message-exchange-1").build();
    }

    @Bean
    public Queue retryQueue() {
        return QueueBuilder.durable("message-retry-1")
                .deadLetterExchange("message-exchange-1")
                .deadLetterRoutingKey("message-key-for-retry-1")
                .withArgument("x-dlq-dead-letter-exchange", null)
                .ttl(5000)
                .build();
    }

    @Bean
    public Binding retryBinding() {
        return BindingBuilder.bind(retryQueue())
                .to(topicExchange())
                .with("message-retry-key-1");
    }
}
