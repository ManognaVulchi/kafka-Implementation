package com.amigoscode;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
    private KafkaTemplate<String, Message> kafkaTemplate; // using dependency injection

    public MessageController(KafkaTemplate<String, Message> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    @PostMapping//as we want to expose this method to clients we add post mapping so
    // that we can issue post req against it
    public void publish(@RequestBody MessageRequest request)//it means from req body we want message req
    {
     Message message = new Message(request.message(), LocalDateTime.now());
     kafkaTemplate.send("amigoscode", message);
    }
}
