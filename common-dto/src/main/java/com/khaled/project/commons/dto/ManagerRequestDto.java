package com.khaled.project.commons.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRequestDto {

    private String client;
    private String projectName;
    private  double budget ;
    private Integer homme;
    private Integer days;
    private Integer dev;
    private Integer qa;
    private  Integer devops;
    private Integer support;
    private String delivery;
    private Integer projectId;

/*    @KafkaListener( topics = "data-manager",
            groupId = "group_id_1" )

   public void Listenner(ConsumerRecord<String,String> data) {
        if (data.key().equals("budget")) {
            bud = Double.parseDouble(data.value());
            System.out.println("key Message : "+" "+data.key());
            System.out.println("Value Message : "+" "+data.value());

        }else { System.out.println("consume failed");}



    }*/

}


