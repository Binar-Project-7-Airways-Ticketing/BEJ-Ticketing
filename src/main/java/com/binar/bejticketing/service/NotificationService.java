package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NotificationService {

    Notification createNotification(Notification notification);
    Notification updateNotification(Notification notification);
    DataNotFoundException deleteNotification(Long id);

    List<Notification> getAllNotificationByUser(Long idUser);
    void isRead(Long id);
    Optional<Notification> getNotificationyId(Long id);
}
