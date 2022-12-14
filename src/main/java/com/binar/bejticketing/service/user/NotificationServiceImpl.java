package com.binar.bejticketing.service.user;

import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.NotificationRepository;
import com.binar.bejticketing.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        Optional<Notification> byId = notificationRepository.findById(notification.getIdNotification());

        if (byId.isPresent()){
            return notificationRepository.save(notification);
        }
        throw new DataNotFoundException(notification.getIdNotification());
    }

    @Override
    public DataNotFoundException deleteNotification(Long id) {
        Optional<Notification> byId = notificationRepository.findById(id);

        if (byId.isPresent()){
            notificationRepository.deleteById(id);
            return null;
        }

        return new DataNotFoundException(id);
    }

    @Override
    public List<Notification> getAllNotificationByUser(Long idUser) {
        return notificationRepository.getNotificationByUser(idUser);
    }

    @Override
    public void isRead(Long id) {
        notificationRepository.isRead(id);
    }

    @Override
    public Optional<Notification> getNotificationyId(Long id) {
        return notificationRepository.findById(id);
    }
}
