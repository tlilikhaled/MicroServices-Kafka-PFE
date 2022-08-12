package com.khaled.project.commons.event;

import com.khaled.project.commons.dto.ManagerRequestDto;
import com.khaled.project.commons.dto.ManagerResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BudgetEvent implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();

    private ManagerRequestDto managerRequestDto;

    private BudgetStatus budgetStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public BudgetEvent(ManagerRequestDto managerRequestDto, BudgetStatus budgetStatus) {
        this.managerRequestDto = managerRequestDto;

        this.budgetStatus = budgetStatus;
    }
}
