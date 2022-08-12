package com.khaled.project.commons.manager.component;

import com.khaled.project.commons.dto.ManagerRequestDto;
import static com.khaled.project.commons.manager.config.BudgetStatusUpdateHandler.sumSalary;
import com.khaled.project.commons.manager.controller.ManagerController;
import com.khaled.project.commons.manager.service.ManagerService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    ManagerController managerController = new ManagerController();


    @Override
    public void run(String... args) throws Exception {
        ManagerRequestDto managerRequestDto = new ManagerRequestDto();
        System.out.println(managerRequestDto.getProjectId());


        String topic = "data-manager";
        String topic1 = "sumSalary-event";

        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_id_1");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        System.out.println("prop worked");
        //creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //Subscribing
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1000));

            for (ConsumerRecord<String, String> data : records) {
                //int k = managerRequestDto.getProjectId();
                //String IdProject = String.valueOf(k);
                System.out.println("consuming Successfully");
                System.out.println(managerRequestDto.getProjectId());
                //System.out.println("idproject:"+ IdProject);
                if (data.key().equals("client")) {
                    managerRequestDto.setClient(data.value());
                } else if (data.key().equals("name")) {
                    managerRequestDto.setProjectName(data.value());
                } else if (data.key().equals("budget")) {
                    managerRequestDto.setBudget(Double.parseDouble(data.value()));
                } else if (data.key().equals("people")) {
                    managerRequestDto.setHomme(Integer.valueOf(data.value()));
                } else if (data.key().equals("days")) {
                    managerRequestDto.setDays(Integer.valueOf(data.value()));
                } else if (data.key().equals("developer")) {
                    managerRequestDto.setDev(Integer.valueOf(data.value()));
                } else if (data.key().equals("quality")) {
                    managerRequestDto.setQa(Integer.valueOf(data.value()));
                } else if (data.key().equals("devops")) {
                    managerRequestDto.setDevops(Integer.valueOf(data.value()));
                } else if (data.key().equals("support")) {
                    managerRequestDto.setSupport(Integer.valueOf(data.value()));
                } else if (data.key().equals("deliveryDate")) {
                    managerRequestDto.setDelivery(data.value());
                    //} else if (data.key().equals(IdProject)) {
                    //  sumSalary = Double.valueOf(data.value());
                    //System.out.println(sumSalary);
                } else {
                    System.out.println("consuming failed");
                }
            }
            System.out.println(managerRequestDto.getProjectId());
            ManagerRequestDto managerRequestDto1 = new ManagerRequestDto(managerRequestDto.getClient(), managerRequestDto.getProjectName(),
                    managerRequestDto.getBudget(), managerRequestDto.getHomme(),
                    managerRequestDto.getDays(), managerRequestDto.getDev(),
                    managerRequestDto.getQa(), managerRequestDto.getDevops(),
                    managerRequestDto.getSupport(), managerRequestDto.getDelivery()
                    , managerRequestDto.getProjectId());
            managerController.createProject(managerRequestDto1);
            //consumer.unsubscribe();
            // consumer.wait(30000);

            consumer.subscribe(Arrays.asList(topic1));

            ConsumerRecords<String, String> record = consumer.poll(Duration.ofSeconds(1000));

            for (ConsumerRecord<String, String> data : record) {
                System.out.println("key" + data.key());
                Integer k = managerRequestDto1.getProjectId();
                String IdProject = String.valueOf(k);
                System.out.println("consuming Successfully");
                System.out.println(managerRequestDto1.getProjectId());
                System.out.println("idproject:" + IdProject);
                if (data.key().equals(IdProject)) {
                    sumSalary = Double.valueOf(data.value());
                    System.out.println(sumSalary);
                } else {
                    System.out.println("consuming failed");
                }
            }


        }
    }
}

