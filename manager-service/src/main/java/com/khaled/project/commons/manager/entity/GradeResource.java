package com.khaled.project.commons.manager.entity;

import com.khaled.project.commons.event.BudgetStatus;
import com.khaled.project.commons.event.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "List_Ressource")
@AllArgsConstructor
@NoArgsConstructor
public class GradeResource {

    @Id
    private Integer projectId;
    private Integer homme;
    @Column(name = "developer")
    private Integer dev;
    @Column(name = "quality")
    private Integer qa;
    private  Integer devops;
    private Integer support;



}
