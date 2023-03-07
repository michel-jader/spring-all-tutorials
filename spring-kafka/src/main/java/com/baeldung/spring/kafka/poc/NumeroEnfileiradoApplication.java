package com.baeldung.spring.kafka.poc;

import com.baeldung.spring.kafka.Greeting;
import com.baeldung.spring.kafka.KafkaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
@EnableKafka
@SpringBootApplication
public class NumeroEnfileiradoApplication {

    public static void main(String[] args) throws Exception {


        ConfigurableApplicationContext context = SpringApplication.run(NumeroEnfileiradoApplication.class, args);

//        NumeroEnfileiradoApplication.MessageProducer producer = context.getBean(NumeroEnfileiradoApplication.MessageProducer.class);


        NumeroEnfileiradoApplication.MessageListener listener =
                context.getBean(NumeroEnfileiradoApplication.MessageListener.class);

//        producer.sendMessage("1");
//        producer.sendMessage("2");
//        producer.sendMessage("3");
//        producer.sendMessage("4");
//        producer.sendMessage("5");


//        System.out.println("INICIOU LISTENER AWAIT");
//
//        listener.latch.await(60, TimeUnit.SECONDS);
//
//        System.out.println("FINALIZOU LISTENER AWAIT");


        Thread.sleep(30000);

        context.close();

    }

    @Bean
    public NumeroEnfileiradoApplication.MessageProducer messageProducer() {
        return new NumeroEnfileiradoApplication.MessageProducer();
    }

    @Bean
    public NumeroEnfileiradoApplication.MessageListener messageListener() {
        return new NumeroEnfileiradoApplication.MessageListener();
    }

    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> numerosKafkaTemplate;

        public void sendMessage(String message) {
            numerosKafkaTemplate.send("numeroTopic", message);
        }

    }

    public static class MessageListener {

//        private CountDownLatch latch = new CountDownLatch(100);

        @KafkaListener(topics = "numeroTopic", groupId = "todosNumeros",
                containerFactory = "todosNumerosKafkaListenerContainerFactory")
        public void listenGroupTodosNumeros(String message) {
            System.out.println("MSG RECEBIDA NO CONSUMER GROUP 'todosNumeros': " + message);
//            latch.countDown();
        }

    }


}
