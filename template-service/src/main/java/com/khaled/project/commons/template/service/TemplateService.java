package com.khaled.project.commons.template.service;

import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.dto.ManagerResponseDto;
import com.khaled.project.commons.dto.TemplateRequestDto;
import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.event.TemplateEvent;
import com.khaled.project.commons.event.TemplateStatus;
import com.khaled.project.commons.template.entity.Template;
import com.khaled.project.commons.template.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {



    @Autowired
    private TemplateRepository templateRepository;


    public Optional<Template> getTemplateById(int id){
        return templateRepository.findById(id);
    }
    public List<Template> getAllTemplate(){
        return templateRepository.findAll();
    }
    /**
     * // get the department id
     * // check the balance availability
     * // if balance sufficient -> template created and deduct nbressource from DB
     * // if nbressource not sufficient -> cancel budget event and update the nbressource in DB
     **/

    @Transactional
    public TemplateEvent newProjectEvent(BudgetEvent budgetEvent){
       // Template template =new Template();

        ManagerRequestDto managerRequestDto = budgetEvent.getManagerRequestDto();
        TemplateRequestDto templateRequestDto = new TemplateRequestDto(managerRequestDto.getClient(),managerRequestDto.getProjectName()
                ,managerRequestDto.getBudget(),managerRequestDto.getHomme(),
                managerRequestDto.getDays(),managerRequestDto.getDelivery(),managerRequestDto.getProjectId());
        LocalDate start = LocalDate.now();
        System.out.println("Template : "+" "+budgetEvent.getBudgetStatus());
        String end1 = managerRequestDto.getDelivery().substring(1,11);
        LocalDate endDate = LocalDate.parse(end1);


        long monthsBetween = ChronoUnit.MONTHS.between(
                start.withDayOfMonth(1),
                endDate.withDayOfMonth(1));
        System.out.println(monthsBetween);
        Double budgetMonthly = managerRequestDto.getBudget()/monthsBetween;
        if(managerRequestDto.getBudget() > 100.00){
            TemplateEvent templateEvent = new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_CREATED);
            templateRepository.save(new Template(managerRequestDto.getProjectId(), managerRequestDto.getClient(), managerRequestDto.getProjectName()
                    ,managerRequestDto.getBudget(),managerRequestDto.getHomme(),
                    managerRequestDto.getDays(),managerRequestDto.getDelivery(),budgetMonthly,
                    templateEvent.getTemplateStatus()));
            return templateEvent; // new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_CREATED);
        }
        else {
            TemplateEvent templateEvent = new TemplateEvent(templateRequestDto,TemplateStatus.TEMPLATE_FAILED);

            return templateEvent;//new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_FAILED);
        }


/*
       return templateRepository.findById(managerRequestDto.getProjectId())
               .filter(rb-> rb.getBudget() > 1000.00)
               .map(rb -> {
                   rb.setBudget(managerRequestDto.getBudget());
                   templateRepository.save(new Template(managerRequestDto.getProjectId(),
                           managerRequestDto.getManagerId(), managerRequestDto.getBudget()));
                   return new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_CREATED);
               }).orElse(
                       new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_FAILED)
               );
*/



    }

    @Transactional
    public void cancelBudgetEvent(BudgetEvent budgetEvent){

        templateRepository.findById(budgetEvent.getManagerRequestDto().getProjectId())
                .ifPresent(tr ->{
                    templateRepository.delete(tr);
                    templateRepository.findById(tr.getProjectId())
                            .ifPresent(rb-> rb.setBudget(rb.getBudget()));
                });

    }

}
