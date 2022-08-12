package com.khaled.project.commons.entity;

import com.khaled.project.commons.event.EmployeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEmployee;
    private Integer projectId;
    private String fullName;
    private String grade;
    @Enumerated(EnumType.STRING)
    private EmployeStatus status;
}
