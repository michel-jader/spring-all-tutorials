package com.baeldung.spring.kafka.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NumeroMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> numerosKafkaTemplate;

    public void sendMessage(String message) {
        numerosKafkaTemplate.send("numeroTopic", message);
    }

}
