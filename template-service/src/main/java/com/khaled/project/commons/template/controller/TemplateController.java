package com.khaled.project.commons.template.controller;

import com.khaled.project.commons.template.entity.Template;
import com.khaled.project.commons.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/{id}")
    public Optional<Template> getTemplateById(@PathVariable int id ){
        return templateService.getTemplateById(id);
    }
    @GetMapping
    public List<Template> getTemplate(){return templateService.getAllTemplate();}
}
