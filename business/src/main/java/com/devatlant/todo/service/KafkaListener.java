package com.devatlant.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("kafka")
public class KafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(id = "foo",
                                        topics = "test-topic",
                                        clientIdPrefix = "moleskine-spring-boot-client"
     )
    public void listen(final String data) {
        log.info("received from kafka topic : '{}' ", data);
    }
}
