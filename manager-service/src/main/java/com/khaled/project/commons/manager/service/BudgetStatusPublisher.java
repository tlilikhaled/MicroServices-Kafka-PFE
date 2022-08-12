package com.khaled.project.commons.manager.service;

import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.event.BudgetEvent;
import com.khaled.project.commons.event.BudgetStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;


@Service
public class BudgetStatusPublisher {

    @Autowired
    private Sinks.Many<BudgetEvent> budgetsinks;

    public void publishBudgetEvent(ManagerRequestDto managerRequestDto, BudgetStatus budgetStatus){
        BudgetEvent budgetEvent = new BudgetEvent(managerRequestDto, budgetStatus);
        budgetsinks.tryEmitNext(budgetEvent);
    }
}
