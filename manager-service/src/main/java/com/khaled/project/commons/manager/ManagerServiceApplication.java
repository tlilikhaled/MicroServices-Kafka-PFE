package com.khaled.project.commons.manager;


import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.manager.controller.ManagerController;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;



@SpringBootApplication
public class ManagerServiceApplication {



    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(ManagerServiceApplication.class);

/*
    URL url = new URL("http", "localhost", 8081, "/manager/project");

        try

    {
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection connection = null;
        if (urlConnection instanceof HttpURLConnection) {
            connection = (HttpURLConnection) urlConnection;
            connection.setRequestMethod("POST");
            connection.connect();
            int code = connection.getResponseCode();
            System.out.println(code);
        } else {
            System.out.println("Please enter an HTTP URL.");
            return;
        }
    } catch(
    IOException e)

    {
        throw new RuntimeException(e);
    }*/
}

}
