package com.khaled.project.common.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String client;
    private Integer projectId;
    private String content;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
}