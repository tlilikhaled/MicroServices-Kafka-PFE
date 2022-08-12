package com.khaled.commons.grade.controller;


import com.khaled.commons.grade.entity.Notification;
import com.khaled.commons.grade.entity.gradeSalairesProject;
import com.khaled.commons.grade.entity.grade_res;
import com.khaled.commons.grade.repository.NotificationRepository;
import com.khaled.project.commons.dto.GradeCostRequestDto;
import com.khaled.project.commons.dto.ManagerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.khaled.commons.grade.service.GradeResService;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/costressources")
public class GradeResController {

    @Autowired
    private GradeResService gradeResService;
    @Autowired
    private NotificationRepository notificationRepository;



    @GetMapping
    public List<gradeSalairesProject> getTemplate(){return gradeResService.getAllCOSTRES();}

    @GetMapping("/notificationbna")
    public List<Notification> getNotifBna() {
        return notificationRepository.findNotificationByClient("'BNA'");
    }
}
