package com.khaled.project.commons.manager.config;

import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.TemplateStatus;
import com.khaled.project.commons.manager.entity.Notification;
import com.khaled.project.commons.manager.entity.NotificationType;
import com.khaled.project.commons.manager.entity.Project;
import com.khaled.project.commons.manager.repository.NotificationRepository;
import com.khaled.project.commons.manager.repository.ProjectRepository;
import com.khaled.project.commons.manager.service.BudgetStatusPublisher;
import com.khaled.project.commons.manager.service.ManagerService;
import com.khaled.project.commons.template.entity.Template;
import com.khaled.project.commons.template.repository.TemplateRepository;
import com.khaled.project.commons.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class BudgetStatusUpdateHandler {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private BudgetStatusPublisher publisher;
    public static Double sumSalary;


    @Transactional
    public void updateProject(int id, Consumer<Project> consumer) {
        repository.findById(id).ifPresent(consumer.andThen(this::updateProject));
    }

    private void updateProject(Project project)  {
        System.out.println("somme : "+sumSalary);
            boolean isTemplateCreated = TemplateStatus.TEMPLATE_CREATED.equals(project.getTemplateStatus());
            System.out.println("Manager 1 : " + " " + project.getTemplateStatus());
            BudgetStatus budgetStatus = isTemplateCreated ? BudgetStatus.BUDGET_SUFFICIENT : BudgetStatus.BUDGET_INSUFFICIENT;
            System.out.println("Manager 2 : " + " " + budgetStatus);
            project.setBudgetStatus(budgetStatus);
        if (project.getBudget() < sumSalary) {
            //repository.delete(project);
            project.setTemplateStatus(TemplateStatus.TEMPLATE_FAILED);
            project.setBudgetStatus(BudgetStatus.BUDGET_INSUFFICIENT);

            Notification notification = new Notification(project.getId(), project.getClient(),
      "Budget insufficient\uD83D\uDE21 You must make other budget more than your sum salary of your project employees Sum salary = "+sumSalary+ "."
                    , NotificationType.WARN);
            managerService.send(notification);
            //Notification notification1 = new Notification(project.getId(),project.getClient(),
              //      "Budget insufficient\uD83D\uDE21 You must make other budget more than your sum salary of your project employees Sum salary = "+sumSalary+ "."
                //    , NotificationType.WARN);
          //  notificationRepository.save(notification1);

        }
        else {
            Notification notification = new Notification(project.getId(), project.getClient(), "Your project was created successfully with Budget"+" "+project.getBudget()+" "+"and Id "+" "+project.getId()+" \uD83D\uDE09✌\uD83D\uDC4F️",NotificationType.INFO);
            managerService.send(notification);
            //Notification notification1 = new Notification(project.getId(),project.getClient(), "Your project was created successfully with Budget"+" "+project.getBudget()+" "+"and Id "+" "+project.getId()+" \uD83D\uDE09✌\uD83D\uDC4F️",NotificationType.INFO);
           // notificationRepository.save(notification1);
        }
            if (!isTemplateCreated) {
                publisher.publishBudgetEvent(convertEntityToDto(project), budgetStatus);

            }
        }

    public ManagerRequestDto convertEntityToDto(Project project) {
        ManagerRequestDto managerRequestDto = new ManagerRequestDto();
        managerRequestDto.setProjectId(project.getId());
        managerRequestDto.setClient(project.getClient());
        managerRequestDto.setProjectName(project.getProjectName());
        managerRequestDto.setHomme(project.getHomme());
        managerRequestDto.setDays(project.getDays());
        managerRequestDto.setDelivery(project.getDelivery());
        managerRequestDto.setBudget(project.getBudget());
        return managerRequestDto;
    }
}
