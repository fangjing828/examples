package com.examples.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

class KafkaConsumerTest {
    @Test
    public void testAcl() {
        boolean consumerSuccess = false;
        Properties properties = KafkaUtils.newProperties("10.123.22.235:9093,10.123.6.125:9093,10.123.19.43:9093", "appId_002009_cn", "ym70T1U710wq");
        try (KafkaConsumer<String, byte[]> consuemr = new KafkaConsumer<>(properties)) {
            consuemr.subscribe(List.of("FUND_POOL_BALANCE_NOTIFY"));
            for (int i = 0; i < 3; i++) {
                ConsumerRecords<String, byte[]> records = consuemr.poll(Duration.ofSeconds(3));
                for (ConsumerRecord<String, byte[]> record : records) {
                    consumerSuccess = true;
                    break;
                }
            }
        }
        assertTrue(consumerSuccess);
    }

    @Test
    public void testNormal() {
        boolean consumerSuccess = false;
        Properties properties = KafkaUtils.newProperties("10.123.22.235:9092,10.123.6.125:9093,10.123.19.43:9092", null, null);
        try (KafkaConsumer<String, byte[]> consuemr = new KafkaConsumer<>(properties)) {
            consuemr.subscribe(List.of("FUND_POOL_BALANCE_NOTIFY"));
            for (int i = 0; i < 3; i++) {
                ConsumerRecords<String, byte[]> records = consuemr.poll(Duration.ofSeconds(3));
                for (ConsumerRecord<String, byte[]> record : records) {
                    consumerSuccess=true;
                    break;
                }
            }
        }
        assertTrue(consumerSuccess);
    }
}