package com.khaled.project.commons.repository;


import com.khaled.project.commons.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
