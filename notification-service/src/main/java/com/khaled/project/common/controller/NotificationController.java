package com.khaled.project.common.controller;


import com.khaled.project.common.entity.Notification;
import com.khaled.project.common.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping("/manager")
    public List<Notification> getnotif(){
        return notificationRepository.findAll();
    }

    @GetMapping("/notBNA")
    public List<Notification> getNotifBNA(){
        return notificationRepository.findNotificationByClient("'BNA'");
    }

    @GetMapping("/notUIB")
    public List<Notification> getNotifUIB(){
        return notificationRepository.findNotificationByClient("'UIB'");
    }
    @GetMapping("/notBIAT")
    public List<Notification> getNotifBIAT(){
        return notificationRepository.findNotificationByClient("'BIAT'");
    }
    @GetMapping("/notQNB")
    public List<Notification> getNotifQNB(){
        return notificationRepository.findNotificationByClient("'QNB'");
    }
}
