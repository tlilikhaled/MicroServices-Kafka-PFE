package com.khaled.project.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeRequestDto {

    private Integer idEmploye;
    private String fullName;
    private String grade;
}
