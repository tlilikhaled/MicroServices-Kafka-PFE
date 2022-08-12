package com.khaled.commons.grade.repository;

import com.khaled.commons.grade.entity.grade_res;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GradeResRepository extends JpaRepository<grade_res,Integer> {



}
