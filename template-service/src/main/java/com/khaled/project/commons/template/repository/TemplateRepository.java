package com.khaled.project.commons.template.repository;

import com.khaled.project.commons.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TemplateRepository extends JpaRepository<Template, Integer> {

}
