package com.khaled.project.commons.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaled.project.commons.component.notifProducer;
import com.khaled.project.commons.dto.EmployeRequestDto;
import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.dto.RessourceRequestDto;
import com.khaled.project.commons.entity.Employe;
import com.khaled.project.commons.entity.Notification;
import com.khaled.project.commons.entity.NotificationType;
import com.khaled.project.commons.entity.Ressource;
import com.khaled.project.commons.event.*;
import com.khaled.project.commons.exception.MapperException;
import com.khaled.project.commons.repository.EmployeInfoRepository;
import com.khaled.project.commons.repository.NotificationRepository;
import com.khaled.project.commons.repository.ResourceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeService {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private EmployeInfoRepository employeInfoRepository;
    @Autowired
    private ResourceInfoRepository resourceInfoRepository;
    @Autowired
    private NotificationRepository notificationRepository;


    public Employe createEmploye(EmployeRequestDto employeRequestDto) {
        System.out.println(employeRequestDto.getFullName());
        System.out.println(employeRequestDto.getGrade());
        Employe employe = new Employe();
        employe = employeInfoRepository.save(convertDtoToEntity(employeRequestDto));
        employeRequestDto.setIdEmploye(employe.getIdEmployee());
        return employe;
    }
    @Transactional
    public void cancelNumberResEvent(BudgetEvent budgetEvent){
        System.out.println("error with budget event");

        resourceInfoRepository.findById(budgetEvent.getManagerRequestDto().getProjectId())
                .ifPresent(tr ->{
                    resourceInfoRepository.delete(tr);
                    resourceInfoRepository.findById(tr.getProjectId())
                            .ifPresent(rb-> rb.setEmployeNumber(rb.getEmployeNumber()));

                });

    }
    LocalDate start = LocalDate.now();
    @Transactional
    public ResourceEvent newResEvent(BudgetEvent budgetEvent) {
        String grade = "developer";
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        RessourceRequestDto ressourceRequestDto = new RessourceRequestDto(managerRequestDto.getProjectId(),
                start,managerRequestDto.getDelivery(),grade,managerRequestDto.getDev());
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDto);
        System.out.println(isEmployeeExist);
        ResourceEvent resourceEvent = new ResourceEvent(ressourceRequestDto, ResoucreStatus.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDto.getDev() != 0) {

            Ressource ressource = new Ressource(ressourceRequestDto.getProjectId(),
                    ressourceRequestDto.getStartDate(), ressourceRequestDto.getEndDate(), ressourceRequestDto.getGradeResource()
                    , ressourceRequestDto.getEmployeeNumber(), resourceEvent.getResoucreStatus());
            resourceInfoRepository.save(ressource);
            Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            send(notification);
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        }else {
            if (managerRequestDto.getDev() != 0) {
                Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);
                send(notification);
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
            return resourceEvent ;

    }
    public ResourceEvent newResEvent1(BudgetEvent budgetEvent) {
        String grade = "quality";
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        RessourceRequestDto ressourceRequestDto = new RessourceRequestDto(managerRequestDto.getProjectId(),
                start,managerRequestDto.getDelivery(),grade,managerRequestDto.getQa());
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDto);
        ResourceEvent resourceEvent = new ResourceEvent(ressourceRequestDto, ResoucreStatus.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDto.getQa() != 0) {
        Ressource ressource = new Ressource(ressourceRequestDto.getProjectId(),
                ressourceRequestDto.getStartDate(), ressourceRequestDto.getEndDate(), ressourceRequestDto.getGradeResource()
                , ressourceRequestDto.getEmployeeNumber(),resourceEvent.getResoucreStatus());

        resourceInfoRepository.save(ressource);
            Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Quality affected to employees with success ", NotificationType.INFO);
            send(notification);
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Quality affected to employees with success ", NotificationType.INFO);

            notificationRepository.save(notification1);*/
        }else {
            if (managerRequestDto.getQa() != 0) {
                Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of quality doesn't exist. We must be recruit other QA ! Contact HR for more information", NotificationType.WARN);
                send(notification);
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of quality doesn't exist. We must be recruit other QA ! Contact HR for more information", NotificationType.WARN);
                notificationRepository.save(notification1);*/
            }
        }
        return resourceEvent ;

    }
    public ResourceEvent newResEvent2(BudgetEvent budgetEvent) {
        String grade = "devops";
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        RessourceRequestDto ressourceRequestDto = new RessourceRequestDto(managerRequestDto.getProjectId(),
                start,managerRequestDto.getDelivery(),grade,managerRequestDto.getDevops());
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDto);
        ResourceEvent resourceEvent = new ResourceEvent(ressourceRequestDto, ResoucreStatus.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDto.getDevops() != 0) {
        Ressource ressource = new Ressource(ressourceRequestDto.getProjectId(),
                ressourceRequestDto.getStartDate(), ressourceRequestDto.getEndDate(), ressourceRequestDto.getGradeResource()
                , ressourceRequestDto.getEmployeeNumber(),resourceEvent.getResoucreStatus());

        resourceInfoRepository.save(ressource);
            Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "DevOps affected to employees with success ", NotificationType.INFO);
            send(notification);
/*
            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "DevOps affected to employees with success ", NotificationType.INFO);

            notificationRepository.save(notification1);*/
        }else {
            if (managerRequestDto.getDevops() != 0) {
                Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of devops doesn't exist. We must be recruit other DevOps! Contact HR for more information", NotificationType.WARN);
                send(notification);
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of devops doesn't exist. We must be recruit other DevOps! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
        return resourceEvent ;

    }
    public ResourceEvent newResEvent3(BudgetEvent budgetEvent) {
        String grade = "support";
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        RessourceRequestDto ressourceRequestDto = new RessourceRequestDto(managerRequestDto.getProjectId(),
                start,managerRequestDto.getDelivery(),grade,managerRequestDto.getSupport());
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDto);
        ResourceEvent resourceEvent = new ResourceEvent(ressourceRequestDto, ResoucreStatus.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDto.getSupport() != 0) {
        Ressource ressource = new Ressource(ressourceRequestDto.getProjectId(),
                ressourceRequestDto.getStartDate(), ressourceRequestDto.getEndDate(), ressourceRequestDto.getGradeResource()
                , ressourceRequestDto.getEmployeeNumber(),resourceEvent.getResoucreStatus());

        resourceInfoRepository.save(ressource);
            Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "IT Supports affected to employees with success ", NotificationType.INFO);
            send(notification);

/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "IT Supports affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        }else {
            if (managerRequestDto.getSupport() != 0) {
                Notification notification = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of IT support doesn't exist. We must be recruit other IT Support! Contact HR for more information", NotificationType.WARN);

                send(notification);
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of IT support doesn't exist. We must be recruit other IT Support! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
        return resourceEvent ;

    }

    public Boolean getEmployeesByGrade(RessourceRequestDto ressourceRequestDto) {
        List<Employe> employees = employeInfoRepository
                .findEmployeeByGrade(ressourceRequestDto.getGradeResource());
        List<Employe> inactiveEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getStatus().equals(EmployeStatus.EMPLOYE_INACTIVE)) {
                //employee.setProjectId(ressourceRequestDto.getProjectId());
                inactiveEmployees.add(employee);
            }
        });
        System.out.println(inactiveEmployees);
        if (ressourceRequestDto.getEmployeeNumber() <= inactiveEmployees.size() ) {
            List<Employe> employeesAffecté =getRandomEmployees(inactiveEmployees, ressourceRequestDto.getEmployeeNumber());
            System.out.println(employeesAffecté);
            employeesAffecté.forEach(employee -> {
                employee.setProjectId(ressourceRequestDto.getProjectId());
                updateEmployee(employee);

            });
            System.out.println(employeesAffecté);
            return true;
        }
        else {

            return false;
        }

    }

    public List<Employe> getRandomEmployees(List<Employe> list, int totalItems) {
        Random rand = new Random();
        List<Employe> employeeList = new ArrayList<>();

        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            employeeList.add(list.get(randomIndex));
            list.remove(randomIndex);
        }
        return employeeList;
    }
    public void updateEmployee(Employe employee) {
        employee.setStatus(EmployeStatus.EMPLOYE_ACTIVE);
        employeInfoRepository.saveAndFlush(employee);
    }
    private Employe convertDtoToEntity(EmployeRequestDto dto) {
        Employe employe = new Employe();
        employe.setFullName(dto.getFullName());
        employe.setGrade(dto.getGrade());
        employe.setStatus(EmployeStatus.EMPLOYE_INACTIVE);

        return employe;
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
