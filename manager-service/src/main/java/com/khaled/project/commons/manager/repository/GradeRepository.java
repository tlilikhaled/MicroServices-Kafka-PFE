package com.khaled.project.commons.manager.repository;

import com.khaled.project.commons.manager.entity.GradeResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeResource,Integer> {
}
