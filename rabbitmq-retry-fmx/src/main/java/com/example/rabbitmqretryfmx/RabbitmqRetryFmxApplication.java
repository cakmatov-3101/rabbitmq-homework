package com.example.rabbitmqretryfmx;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqRetryFmxApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqRetryFmxApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(AmqpTemplate template) {
		return args -> {
			template.convertAndSend("message-exchange-1", "message-key-with-retry-fmx-1", "Message 1");
			template.convertAndSend("message-exchange-1", "message-key-with-retry-fmx-1", "Message 2");
		};
	}
}
