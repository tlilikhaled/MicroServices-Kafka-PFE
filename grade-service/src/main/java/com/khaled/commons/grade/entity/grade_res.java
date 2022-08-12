package com.khaled.commons.grade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class grade_res {
    @Id
    @GeneratedValue
    private Integer grade_id;
    private String grade;
    private Double salaire;
    private LocalDate dateUpdate = LocalDate.now();
}
