package com.baeldung.spring.kafka.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@EnableKafka
@SpringBootApplication
public class NumeroEnfileiradoApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(NumeroEnfileiradoApplication.class, args);

    }





}
