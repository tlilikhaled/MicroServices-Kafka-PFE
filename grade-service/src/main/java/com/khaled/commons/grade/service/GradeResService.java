package com.khaled.commons.grade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaled.commons.grade.component.notifProducer;
import com.khaled.commons.grade.component.sumProducer;
import com.khaled.commons.grade.entity.*;
import com.khaled.commons.grade.exception.MapperException;
import com.khaled.commons.grade.repository.GradeSalairesProjectRepository;
import com.khaled.commons.grade.repository.NotificationRepository;
import com.khaled.commons.grade.repository.SumSalaryRepository;
import com.khaled.project.commons.dto.GradeCostRequestDto;
import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.event.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.khaled.commons.grade.repository.GradeResRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class GradeResService {
    @Autowired
    private NotificationRepository notificationRepository;
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private GradeResRepository gradeResRepository;

    @Autowired
    private SumSalaryRepository sumSalaryRepository;

    @Autowired
    private GradeSalairesProjectRepository GradeSalairesProjectRepository;
    @Autowired
    private GradeSalairesProjectRepository GradeSalairesProjectRepository1;
    @Autowired
    private GradeSalairesProjectRepository GradeSalairesProjectRepository2;
    @Autowired
    private GradeSalairesProjectRepository GradeSalairesProjectRepository3;
    @Autowired
    sumProducer sumProducer = new sumProducer();
     grade_res gradeRes = new grade_res();
    grade_res gradeRes1 = new grade_res();
    grade_res gradeRes2 = new grade_res();
    grade_res gradeRes3 = new grade_res();
    public  Double somme;
    public  Double somme1;
    public  Double somme2;
    public  Double somme3;


    public List<gradeSalairesProject> getAllCOSTRES() {
        return GradeSalairesProjectRepository.findAll();
    }

    public Optional<grade_res> findDevById(int id){
        Optional<grade_res> grade_res;
        grade_res = gradeResRepository.findById(id);
            BeanUtils.copyProperties(grade_res.get(),gradeRes);
            System.out.println(gradeRes.getGrade());
            System.out.println(gradeRes.getSalaire());

        return Optional.ofNullable(gradeRes);
    }
    public Optional<grade_res> findQaById(int id){
        Optional<grade_res> grade_res;
        grade_res = gradeResRepository.findById(id);
        BeanUtils.copyProperties(grade_res.get(),gradeRes1);
        return Optional.ofNullable(gradeRes1);
    }
    public Optional<grade_res> findDevopsById(int id){
        Optional<grade_res> grade_res;
        grade_res = gradeResRepository.findById(id);
        BeanUtils.copyProperties(grade_res.get(),gradeRes2);
        return Optional.ofNullable(gradeRes2);
    }
    public Optional<grade_res> findSupportById(int id){
        Optional<grade_res> grade_res;
        grade_res = gradeResRepository.findById(id);
        BeanUtils.copyProperties(grade_res.get(),gradeRes3);
        return Optional.ofNullable(gradeRes3);
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

    @Transactional
    public void cancelNumberResEvent(BudgetEvent budgetEvent){
        System.out.println("error with budget event");

        GradeSalairesProjectRepository.findById(budgetEvent.getManagerRequestDto().getProjectId())
                .ifPresent(tr ->{
                    GradeSalairesProjectRepository.delete(tr);
                    GradeSalairesProjectRepository.findById(tr.getProjectId())
                            .ifPresent(rb-> rb.setNombre(rb.getNombre()));

                });

    }
    @Transactional
    public GradeCostEvent newCostEvent(BudgetEvent budgetEvent) {
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        GradeCostRequestDto gradeCostRequestDto = new GradeCostRequestDto(managerRequestDto.getProjectId(),
                gradeRes.getGrade(),gradeRes.getSalaire(),managerRequestDto.getDev());
        somme = gradeCostRequestDto.getSalaire() * gradeCostRequestDto.getNombre();
        GradeCostEvent gradeCostEvent = new GradeCostEvent(gradeCostRequestDto, GradeCostStatus.GRADE_COST_DEFINED);
        gradeSalairesProject gradeSalairesProject = new gradeSalairesProject(managerRequestDto.getProjectId(),
                gradeCostRequestDto.getGrade(), gradeCostRequestDto.getSalaire(), gradeCostRequestDto.getNombre()
                ,somme,gradeCostEvent.getGradeCostStatus());
        GradeSalairesProjectRepository.save(gradeSalairesProject);
        Notification notification = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
                "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        send(notification);
        //Notification notification1 = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
          //      "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        //notificationRepository.save(notification1);

        return gradeCostEvent ;

    }

    @Transactional
    public GradeCostEvent newCostEvent1(BudgetEvent budgetEvent)  {
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        GradeCostRequestDto gradeCostRequestDto1 = new GradeCostRequestDto(managerRequestDto.getProjectId(),
                gradeRes1.getGrade(),gradeRes1.getSalaire(),managerRequestDto.getQa());
        somme1 = gradeCostRequestDto1.getSalaire() * gradeCostRequestDto1.getNombre();
        GradeCostEvent gradeCostEvent1 = new GradeCostEvent(gradeCostRequestDto1, GradeCostStatus.GRADE_COST_DEFINED);
        GradeSalairesProjectRepository1.save(new gradeSalairesProject(gradeCostRequestDto1.getProjectId(),
                gradeCostRequestDto1.getGrade(), gradeCostRequestDto1.getSalaire(), gradeCostRequestDto1.getNombre(),somme1,gradeCostEvent1.getGradeCostStatus()));
    return gradeCostEvent1;
    }
    @Transactional
    public GradeCostEvent newCostEvent2(BudgetEvent budgetEvent)  {
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        GradeCostRequestDto gradeCostRequestDto2 = new GradeCostRequestDto(managerRequestDto.getProjectId(),
                gradeRes2.getGrade(),gradeRes2.getSalaire(),managerRequestDto.getDevops());
        somme2 = gradeCostRequestDto2.getSalaire() * gradeCostRequestDto2.getNombre();
        GradeCostEvent gradeCostEvent2 = new GradeCostEvent(gradeCostRequestDto2, GradeCostStatus.GRADE_COST_DEFINED);
        GradeSalairesProjectRepository2.save(new gradeSalairesProject(gradeCostRequestDto2.getProjectId(),
                gradeCostRequestDto2.getGrade(), gradeCostRequestDto2.getSalaire(), gradeCostRequestDto2.getNombre(),somme2,gradeCostEvent2.getGradeCostStatus()));
        return gradeCostEvent2;
    }
    @Transactional
    public GradeCostEvent newCostEvent3(BudgetEvent budgetEvent) throws Exception {
        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        GradeCostRequestDto gradeCostRequestDto3 = new GradeCostRequestDto(managerRequestDto.getProjectId(),
                gradeRes3.getGrade(),gradeRes3.getSalaire(),managerRequestDto.getSupport());
        somme3 = gradeCostRequestDto3.getSalaire() * gradeCostRequestDto3.getNombre();
        GradeCostEvent gradeCostEvent3 = new GradeCostEvent(gradeCostRequestDto3, GradeCostStatus.GRADE_COST_DEFINED);
        GradeSalairesProjectRepository3.save(new gradeSalairesProject(gradeCostRequestDto3.getProjectId(),
                gradeCostRequestDto3.getGrade(), gradeCostRequestDto3.getSalaire(), gradeCostRequestDto3.getNombre(),somme3,gradeCostEvent3.getGradeCostStatus()));
        LocalDate start = LocalDate.now();
        String end1 = managerRequestDto.getDelivery().substring(1,11);
        LocalDate endDate = LocalDate.parse(end1);
        long monthsBetween = ChronoUnit.MONTHS.between(
                start.withDayOfMonth(1),
                endDate.withDayOfMonth(1));
        Double sommes = (somme+somme1+somme2+somme3) * monthsBetween;
        int k = gradeCostRequestDto3.getProjectId();
        System.out.println(k);
        String key = String.valueOf(k);
        System.out.println(key);
        double s = sommes;
        String sum = String.valueOf(s);
        sumProducer.produceMessage(key,sum);
        sumSalaryRepository.save(new sumSalaryInProject(gradeCostRequestDto3.getProjectId(),sommes,monthsBetween));
        return gradeCostEvent3;
    }


}
