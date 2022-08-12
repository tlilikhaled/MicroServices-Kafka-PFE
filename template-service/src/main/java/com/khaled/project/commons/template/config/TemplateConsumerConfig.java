package com.khaled.project.commons.template.config;

import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.TemplateEvent;
import com.khaled.project.commons.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class TemplateConsumerConfig {


    @Autowired
    private TemplateService templateService;

    @Bean
    public Function<Flux<BudgetEvent>, Flux<TemplateEvent>> templateProcessor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processTemplate);
    }

    private Mono<TemplateEvent> processTemplate(BudgetEvent budgetEvent) {
        /**
         * // get the department id
         * // check the balance availability
         * // if balance sufficient -> template created and deduct nbressource from DB
         * // if nbressource not sufficient -> cancel budget event and update the nbressource in DB
         **/
        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()->this.templateService.newProjectEvent(budgetEvent));
        }else{
            return Mono.fromRunnable(()->this.templateService.cancelBudgetEvent(budgetEvent));
        }
    }
}
