package com.examples.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;

import java.util.Properties;

public class KafkaUtils {
    public static Properties newProperties(String server, String user, String password) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        // 序列化方式
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        // 消费组分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "spring-boot-sample-xyz");
        // 手动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 100 * 1024 * 1024);
        // 获取最新的消息，丢失历史消息
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        if (isNotBlank(user) && isNotBlank(password)) {
            properties.put("sasl.mechanism", "SCRAM-SHA-256");
            properties.put("security.protocol", "SASL_PLAINTEXT");
            properties.put("sasl.jaas.config", String.format("org.apache.kafka.common.security.scram.ScramLoginModule required username='%s' password='%s';", user, password));
        }
        return properties;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
