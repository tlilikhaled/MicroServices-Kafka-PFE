package com.khaled.project.commons.controller;

import com.khaled.project.commons.dto.EmployeRequestDto;
import com.khaled.project.commons.entity.Employe;
import com.khaled.project.commons.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private EmployeService employeService;
    @PostMapping("/create")
    public Employe addEmploye(EmployeRequestDto employeRequestDto){
        return employeService.createEmploye(employeRequestDto);
    }
}
