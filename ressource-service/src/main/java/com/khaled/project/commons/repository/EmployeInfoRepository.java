package com.khaled.project.commons.repository;

import com.khaled.project.commons.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeInfoRepository extends JpaRepository<Employe, Integer> {

   @Query("select e from Employe e where e.grade = :grade")
    List<Employe> findEmployeeByGrade(@Param("grade") String grade);
}
