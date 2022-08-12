package com.khaled.commons.grade.component;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class sumProducer {
    static String TOPIC = "sumSalary-event";
    private static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
    public static void produceMessage(String cle, String msg) throws Exception {
        final Producer<String, String> producer = createProducer();


        try {

                final ProducerRecord<String, String> record =
                        new ProducerRecord<>(TOPIC, cle, msg);

                RecordMetadata metadata = producer.send(record).get();



        } finally {
        producer.flush();
        producer.close();
        }
    }


}
