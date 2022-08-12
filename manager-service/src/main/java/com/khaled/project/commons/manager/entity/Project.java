package com.khaled.project.commons.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "ProjectInfoRequest")
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Integer Id;
    private String client;
    private String projectName;
    private double budget;
    private Integer homme;
    private Integer days;
    private String delivery;
    @Enumerated(EnumType.STRING)
    private BudgetStatus budgetStatus;
    @Enumerated(EnumType.STRING)
    private TemplateStatus templateStatus;

}
