package com.amigoscode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaExampleApplication {

    public static void main(String[] args) {

        SpringApplication.run(KafkaExampleApplication.class, args);
    }
    @Bean
    //this method will run as soon as we start the application
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Message> kafkaTemplate) // using dependency injection
    {
        return args -> {
            for(int i=0;i<100;i++) {
                kafkaTemplate.send("amigoscode",
                        new Message("hello kafka :)" + i,
                        LocalDateTime.now()
                        )
                );
            }
        };
    }
}
