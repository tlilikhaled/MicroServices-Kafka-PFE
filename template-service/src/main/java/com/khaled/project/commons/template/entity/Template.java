package com.khaled.project.commons.template.entity;

import com.khaled.project.commons.event.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {
    @Id
    private Integer projectId;
    private String client;
    private String projectName;
    private  double budget ;
    private Integer homme;
    private Integer days;
    private String delivery;
    private double budgetMonthly;
    @Enumerated(EnumType.STRING)
    private TemplateStatus templateStatus;





}
