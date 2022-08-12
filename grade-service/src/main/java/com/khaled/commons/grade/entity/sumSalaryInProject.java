package com.khaled.commons.grade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class sumSalaryInProject {

    @Id
    private Integer projectId;
    private Double sumSalary;
    private Long duration;
}
