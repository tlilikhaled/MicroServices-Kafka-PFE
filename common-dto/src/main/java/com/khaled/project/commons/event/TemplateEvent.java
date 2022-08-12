package com.khaled.project.commons.event;

import com.khaled.project.commons.dto.TemplateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data

@NoArgsConstructor
public class TemplateEvent implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();
    private TemplateRequestDto templateRequest;
    private TemplateStatus templateStatus;
    @Override
    public UUID getEventId() {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }

    public TemplateEvent(TemplateRequestDto templateRequest, TemplateStatus templateStatus) {
        this.templateRequest = templateRequest;
        this.templateStatus = templateStatus;
    }
}
