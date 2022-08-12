package com.khaled.project.commons.entity;

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
    private Integer Id;
    private Integer projectId;
    private String client;
    private String content;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public Notification(Integer projectId, String content, NotificationType type) {
        this.projectId = projectId;
        this.content = content;
        this.type = type;
    }
    public Notification(Integer projectId, String client, String content, NotificationType type) {
        this.projectId = projectId;
        this.client = client;
        this.content = content;
        this.type = type;
    }
}