package com.scylla.demo.web;

import com.scylla.demo.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "/publish/messages")
    public void sendMessageToKafkaTopics(@RequestParam("messages") String message) {
        this.producer.transaction(message);
    }
}
