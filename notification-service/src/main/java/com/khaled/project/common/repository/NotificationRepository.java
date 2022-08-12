package com.khaled.project.common.repository;

import com.khaled.project.common.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    @Query("select n from Notification n where n.client = :client")
    List<Notification> findNotificationByClient(@Param("client") String client);
}
