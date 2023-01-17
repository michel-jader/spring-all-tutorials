package com.baeldung.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class NumeroEnfileiradoApplication {


    public static void main_old(String[] args) throws Exception {
        // 1 - enfileirar numeros...

        // 2 - tudo que for numero par, ler no consumer par.

        // 3 - tudo que for numero impar, ler no consumer impar.

        // 4 - ler no consumer geral, qualquer numero.

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);
    }


    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

        @Value(value = "${message.topic.name}")
        private String topicName;

        @Value(value = "${partitioned.topic.name}")
        private String partitionedTopicName;

        @Value(value = "${filtered.topic.name}")
        private String filteredTopicName;

        @Value(value = "${greeting.topic.name}")
        private String greetingTopicName;

        public void sendMessage(String message) {

            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                            .offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                }
            });
        }

        public void sendMessageToPartition(String message, int partition) {
            kafkaTemplate.send(partitionedTopicName, partition, null, message);
        }

        public void sendMessageToFiltered(String message) {
            kafkaTemplate.send(filteredTopicName, message);
        }

        public void sendGreetingMessage(Greeting greeting) {
            greetingKafkaTemplate.send(greetingTopicName, greeting);
        }
    }


}
