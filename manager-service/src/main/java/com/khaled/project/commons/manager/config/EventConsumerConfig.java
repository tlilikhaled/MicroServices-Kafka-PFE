package com.khaled.project.commons.manager.config;

import com.khaled.project.commons.event.TemplateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {


    @Autowired
    private BudgetStatusUpdateHandler handler;


    @Bean
    public Consumer<TemplateEvent> templateEventConsumer(){
        //listen template-event-topic
        //will check template status
        //if  template status created -> define the budget and create project
        //if template  status failed -> cancel the project
        return (template)-> handler.updateProject(template.getTemplateRequest().getProjectId(),tb->{
            tb.setTemplateStatus(template.getTemplateStatus());
        });
    }


    }

