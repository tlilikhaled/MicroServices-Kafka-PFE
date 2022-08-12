package com.khaled.project.commons.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.manager.component.notifProducer;
import com.khaled.project.commons.manager.entity.GradeResource;
import com.khaled.project.commons.manager.entity.Notification;
import com.khaled.project.commons.manager.entity.Project;
import com.khaled.project.commons.manager.repository.GradeRepository;
import com.khaled.project.commons.manager.repository.ProjectRepository;
import com.khaled.project.commons.manager.exception.MapperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private GradeRepository gradeRepository;


    @Autowired
    private BudgetStatusPublisher budgetStatusPublisher;

    @Transactional
    public  Project createProject(ManagerRequestDto managerRequestDto){
        Project newproject = new Project();
        GradeResource newListressource = new GradeResource();
        if(managerRequestDto.getBudget() > 1000.0){
            newproject = projectRepository.save(convertDtoToEntity(managerRequestDto));
        managerRequestDto.setProjectId(newproject.getId());

         newListressource = gradeRepository.save(convertToEntity(managerRequestDto));
        //produce kafka event with status BUDGET_DEFINED

        budgetStatusPublisher.publishBudgetEvent(managerRequestDto, BudgetStatus.BUDGET_DEFINED);
        }
        else {
            projectRepository.delete(convertDtoToEntity(managerRequestDto));
             budgetStatusPublisher.publishBudgetEvent(managerRequestDto,BudgetStatus.BUDGET_NOT_DEFINED);
        }
        return newproject;
    }


    private Project convertDtoToEntity(ManagerRequestDto dto) {
        Project  project = new Project();
        project.setClient(dto.getClient());
        project.setProjectName(dto.getProjectName());
        System.out.println(dto.getProjectName());
        project.setBudget(dto.getBudget());
        project.setHomme(dto.getHomme());
        project.setDays(dto.getDays());
        project.setDelivery(dto.getDelivery());
        project.setBudgetStatus(BudgetStatus.BUDGET_DEFINED);

        return project;
    }

   private GradeResource convertToEntity(ManagerRequestDto dto){
        GradeResource gradeResource = new GradeResource();
        gradeResource.setProjectId(dto.getProjectId());
        gradeResource.setHomme(dto.getHomme());
        gradeResource.setDev(dto.getDev());
        gradeResource.setQa(dto.getQa());
        gradeResource.setDevops(dto.getDevops());
        gradeResource.setSupport(dto.getSupport());
        return gradeResource;
    }
    public void send(Notification notification)  {
        notifProducer.sendNotification( toJson(notification));
    }

    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
