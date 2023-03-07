package com.baeldung.spring.kafka.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Executor implements CommandLineRunner {

    @Autowired
    private NumeroMessageProducer producer;

    @Override
    public void run(String... args) throws Exception {

        producer.sendMessage("1");
        producer.sendMessage("2");
        producer.sendMessage("3");
        producer.sendMessage("4");
        producer.sendMessage("5");

    }


}
