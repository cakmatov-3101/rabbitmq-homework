package com.example.rabbitmqttldlq.config;

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
        return QueueBuilder.durable("message-dlq-1")
                .build();
    }

    @Bean
    public Binding retryBinding() {
        return BindingBuilder.bind(retryQueue())
                .to(topicExchange())
                .with("message-dlq-key-1");
    }

}
