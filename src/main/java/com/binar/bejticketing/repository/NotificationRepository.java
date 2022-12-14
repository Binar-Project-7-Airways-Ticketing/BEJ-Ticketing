package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = TRUE WHERE n.idNotification = :id")
    void isRead(Long id);

    @Query("SELECT n FROM Notification n WHERE n.user.id = :id")
    List<Notification> getNotificationByUser(Long id);
}
