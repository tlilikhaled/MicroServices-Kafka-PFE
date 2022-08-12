package com.khaled.project.commons;

import com.khaled.project.commons.dto.ManagerRequestDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


@SpringBootApplication
 public class CommonDtoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonDtoApplication.class);

    }
}