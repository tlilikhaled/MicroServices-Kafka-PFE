package com.khaled.project.commons.dto;

import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDto {

    private Integer projectId;
    private Integer managerId;
    private double budget;
    private BudgetStatus budgetStatus;





}
