package com.khaled.commons.grade.repository;

import com.khaled.commons.grade.entity.sumSalaryInProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SumSalaryRepository extends JpaRepository<sumSalaryInProject,Integer> {
}
