package com.khaled.project.commons.event;

import com.khaled.project.commons.dto.EmployeRequestDto;
import com.khaled.project.commons.dto.ManagerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmployeEvent implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();

    private EmployeRequestDto employeRequestDto;

    private EmployeStatus employeStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public EmployeEvent(EmployeRequestDto employeRequestDto, EmployeStatus employeStatus) {
        this.employeRequestDto = employeRequestDto;
        this.employeStatus = employeStatus;
    }
}
