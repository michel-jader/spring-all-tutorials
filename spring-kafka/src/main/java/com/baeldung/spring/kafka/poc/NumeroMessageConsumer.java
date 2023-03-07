package com.baeldung.spring.kafka.poc;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NumeroMessageConsumer {

    @KafkaListener(topics = "numeroTopic", groupId = "todosNumeros",
            containerFactory = "todosNumerosKafkaListenerContainerFactory")
    public void listenGroupTodosNumeros(String message) {
        System.out.println("MSG RECEBIDA NO CONSUMER GROUP 'todosNumeros': " + message);
    }


}
