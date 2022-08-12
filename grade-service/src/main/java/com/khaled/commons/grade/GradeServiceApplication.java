package com.khaled.commons.grade;

import com.khaled.commons.grade.repository.GradeResRepository;
import com.khaled.commons.grade.service.GradeResService;
import com.khaled.project.commons.event.BudgetEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class GradeServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(GradeServiceApplication.class);
    }
}
