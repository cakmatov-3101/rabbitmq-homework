package com.example.rabbitmqttldlq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class RabbitmqTtlDlqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqTtlDlqApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(AmqpTemplate template) {
		return args -> {
			IntStream.range(0, 3).forEach(i -> {
				template.convertAndSend("message-exchange-1", "message-key-for-retry-1", "Message " + i);
			});
		};
	}

}
