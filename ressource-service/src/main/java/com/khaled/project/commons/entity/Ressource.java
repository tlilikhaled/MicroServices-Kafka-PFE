package com.khaled.project.commons.entity;

import com.khaled.project.commons.event.ResoucreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resourceId;
    private Integer projectId;
    private LocalDate startDate;
    private String endDate;
    private String gradeResource;
    private Integer employeNumber;
    @Enumerated(EnumType.STRING)
    private ResoucreStatus resoucreStatus;

    public Ressource(Integer projectId, LocalDate startDate, String endDate, String gradeResource, Integer employeeNumber, ResoucreStatus resoucreStatus) {
    }
}
