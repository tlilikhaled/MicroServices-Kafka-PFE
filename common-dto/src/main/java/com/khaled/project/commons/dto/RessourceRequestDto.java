package com.khaled.project.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RessourceRequestDto {


    private Integer projectId;
    private LocalDate startDate ;
    private String endDate;
    private String gradeResource;
    private Integer employeeNumber;


}
