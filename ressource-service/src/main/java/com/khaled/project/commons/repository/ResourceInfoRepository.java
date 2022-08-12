package com.khaled.project.commons.repository;

import com.khaled.project.commons.entity.Ressource;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceInfoRepository extends JpaRepository<Ressource, Integer> {
}
