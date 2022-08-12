package com.khaled.project.commons.event;

import com.khaled.project.commons.dto.EmployeRequestDto;
import com.khaled.project.commons.dto.RessourceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResourceEvent implements Event{
    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();

    private RessourceRequestDto ressourceRequestDto;

    private ResoucreStatus resoucreStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public ResourceEvent(RessourceRequestDto ressourceRequestDto, ResoucreStatus resoucreStatus) {
        this.ressourceRequestDto = ressourceRequestDto;
        this.resoucreStatus = resoucreStatus;
    }
}
