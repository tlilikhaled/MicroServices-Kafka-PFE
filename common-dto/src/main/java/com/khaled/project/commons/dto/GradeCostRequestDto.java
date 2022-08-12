package com.khaled.project.commons.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeCostRequestDto {

    private Integer projectId ;
    private String grade ;
    private Double salaire;
    private Integer nombre;


    // private Double sum;


}
