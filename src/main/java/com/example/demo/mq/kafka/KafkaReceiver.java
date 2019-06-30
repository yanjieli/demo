package com.example.demo.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaReceiver {
    @KafkaListener(topics = TopicConstants.TEST)
    public void receive(ConsumerRecord<String, String> record) {
        log.info("record:{}", record);
    }
}
