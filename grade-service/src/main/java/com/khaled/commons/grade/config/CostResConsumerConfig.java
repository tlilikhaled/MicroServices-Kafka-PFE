package com.khaled.commons.grade.config;

import com.khaled.commons.grade.service.GradeResService;
import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.GradeCostEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class CostResConsumerConfig {


    @Autowired
    private GradeResService gradeResService;

    @Bean
    public Function<Flux<BudgetEvent>, Flux<GradeCostEvent>> costResourceProcessor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processCostRes);
    }
    @Bean
    public Function<Flux<BudgetEvent>, Flux<GradeCostEvent>> costResource1Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processCostRes1);
    }
    @Bean
    public Function<Flux<BudgetEvent>, Flux<GradeCostEvent>> costResource2Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processCostRes2);
    }
    @Bean
    public Function<Flux<BudgetEvent>, Flux<GradeCostEvent>> costResource3Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processCostRes3);
    }

    private Mono<GradeCostEvent> processCostRes(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.gradeResService.newCostEvent(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.gradeResService.cancelNumberResEvent(budgetEvent));
        }
    }
    private Mono<GradeCostEvent> processCostRes1(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.gradeResService.newCostEvent1(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.gradeResService.cancelNumberResEvent(budgetEvent));
        }
    }
    private Mono<GradeCostEvent> processCostRes2(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.gradeResService.newCostEvent2(budgetEvent));

        }else{

            return Mono.fromRunnable(()->this.gradeResService.cancelNumberResEvent(budgetEvent));
        }
    }
    private Mono<GradeCostEvent> processCostRes3(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> {
                try {
                    return this.gradeResService.newCostEvent3(budgetEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        }else{
            return Mono.fromRunnable(()->this.gradeResService.cancelNumberResEvent(budgetEvent));
        }
    }

}
