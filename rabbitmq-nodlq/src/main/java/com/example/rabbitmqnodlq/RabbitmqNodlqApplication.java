package com.example.rabbitmqnodlq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class RabbitmqNodlqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqNodlqApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(AmqpTemplate template) {
		return args -> {
			template.convertAndSend("message-exchange-1", "message-key-1", "Message 1");
			template.convertAndSend("message-exchange-1", "message-key-1", "Message 2");
		};
	}


}
