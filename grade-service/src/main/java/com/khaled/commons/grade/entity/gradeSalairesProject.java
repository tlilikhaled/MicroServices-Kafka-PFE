package com.khaled.commons.grade.entity;

import com.khaled.project.commons.event.GradeCostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class gradeSalairesProject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer projectId;
    private String grade;
    private Double salaire;
    private Integer nombre;
    private Double somme;
    @Enumerated(EnumType.STRING)
    private GradeCostStatus gradeCostStatus;


    public gradeSalairesProject(Integer projectId, String grade, Double salaire, Integer nombre, Double somme, GradeCostStatus gradeCostStatus) {
        this.id = id;
        this.projectId = projectId;
        this.grade = grade;
        this.salaire = salaire;
        this.nombre = nombre;
        this.somme = somme;
        this.gradeCostStatus = gradeCostStatus;
    }


}
