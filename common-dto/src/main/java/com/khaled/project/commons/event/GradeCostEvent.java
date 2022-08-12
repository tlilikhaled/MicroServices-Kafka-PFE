package com.khaled.project.commons.event;

import com.khaled.project.commons.dto.GradeCostRequestDto;
import com.khaled.project.commons.dto.TemplateRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
public class GradeCostEvent implements Event{

    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();
    private GradeCostRequestDto gradeCostRequestDto;
    private GradeCostStatus gradeCostStatus;
    @Override
    public UUID getEventId() {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }

    public GradeCostEvent(GradeCostRequestDto gradeCostRequestDto, GradeCostStatus gradeCostStatus) {
        this.gradeCostRequestDto = gradeCostRequestDto;
        this.gradeCostStatus = gradeCostStatus;
    }


}
