package com.khaled.project.commons.manager.controller;

import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.manager.entity.GradeResource;
import com.khaled.project.commons.manager.entity.Notification;
import com.khaled.project.commons.manager.entity.Project;
import com.khaled.project.commons.manager.repository.GradeRepository;
import com.khaled.project.commons.manager.repository.NotificationRepository;
import com.khaled.project.commons.manager.repository.ProjectRepository;
import com.khaled.project.commons.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/project")
    public Project createProject(ManagerRequestDto managerRequestDto) {
        return managerService.createProject(managerRequestDto);
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/grades")
    public List<GradeResource> getListResources() {
        return gradeRepository.findAll();
    }


    @GetMapping("/notificationatb")
    public List<Notification> getNotifAtb() {
        return notificationRepository.findNotificationByClient("'ATB'");
    }

    @GetMapping("/notificationbna")
    public List<Notification> getNotifBna() {
        return notificationRepository.findNotificationByClient("'BNA'");
    }
}
