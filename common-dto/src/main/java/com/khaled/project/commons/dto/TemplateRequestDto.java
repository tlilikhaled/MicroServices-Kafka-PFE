package com.khaled.project.commons.dto;

import com.khaled.project.commons.event.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequestDto {
    private String client;
    private String projectName;
    private  double budget ;
    private Integer homme;
    private Integer days;
    private String delivery;
    private Integer projectId;

}
