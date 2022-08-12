package com.khaled.project.commons.manager.config;

import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.template.repository.TemplateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class BudgetPublisherConfig {


    @Bean
    public Sinks.Many<BudgetEvent> budgetSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<BudgetEvent>> budgetSupplier (Sinks.Many<BudgetEvent> sinks){
        return sinks::asFlux;
    }
}
