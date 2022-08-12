package com.khaled.commons.grade.component;

import com.khaled.commons.grade.entity.grade_res;
import com.khaled.commons.grade.repository.GradeResRepository;
import com.khaled.commons.grade.service.GradeResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    GradeResService gradeResService;
    @Override
    public void run(String... args) throws Exception {
        gradeResService.findDevById(1);
        gradeResService.findQaById(2);
        gradeResService.findDevopsById(3);
        gradeResService.findSupportById(4);
    }
}
