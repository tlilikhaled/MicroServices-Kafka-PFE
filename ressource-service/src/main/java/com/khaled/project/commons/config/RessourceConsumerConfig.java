package com.khaled.project.commons.config;


import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.event.BudgetStatus;

import com.khaled.project.commons.event.ResourceEvent;
import com.khaled.project.commons.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class RessourceConsumerConfig {


    @Autowired
    private EmployeService employeService ;

    @Bean
    public Function<Flux<BudgetEvent>, Flux<ResourceEvent>> ResourceProcessor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processRes);
    }

    @Bean
    public Function<Flux<BudgetEvent>, Flux<ResourceEvent>> Resource1Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processRes1);
    }
    @Bean
    public Function<Flux<BudgetEvent>, Flux<ResourceEvent>> Resource2Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processRes2);
    }
    @Bean
    public Function<Flux<BudgetEvent>, Flux<ResourceEvent>> Resource3Processor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processRes3);
    }


    private Mono<ResourceEvent> processRes(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.employeService.newResEvent(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.employeService.cancelNumberResEvent(budgetEvent));
        }
   }
     private Mono<ResourceEvent> processRes1(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.employeService.newResEvent1(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.employeService.cancelNumberResEvent(budgetEvent));
        }
    }
    private Mono<ResourceEvent> processRes2(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> this.employeService.newResEvent2(budgetEvent));

        }else{

            return Mono.fromRunnable(()->this.employeService.cancelNumberResEvent(budgetEvent));
        }
    }
    private Mono<ResourceEvent> processRes3(BudgetEvent budgetEvent) {

        if(BudgetStatus.BUDGET_DEFINED.equals(budgetEvent.getBudgetStatus())){
            return  Mono.fromSupplier(()-> {
                try {
                    return this.employeService.newResEvent3(budgetEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        }else{
            return Mono.fromRunnable(()->this.employeService.cancelNumberResEvent(budgetEvent));
        }
    }

}
