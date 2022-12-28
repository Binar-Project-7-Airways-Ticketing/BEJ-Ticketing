package com.binar.bejticketing.service.user;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void createNotification(){
        Notification notification = new Notification();
        notification.setCategory("BOOKING");
        notification.setTitle("Pembayaran");
        notification.setMessage("Segera Lakukan Pembayaran");

        notificationService.createNotification(notification);

        ArgumentCaptor<Notification> userEntityArgumentCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(userEntityArgumentCaptor.capture());

        Notification captureNotification = userEntityArgumentCaptor.getValue();
        assertThat(captureNotification).isEqualTo(notification);
    }

    @Test
    void updatNotification(){
        Notification notification = new Notification();
        notification.setIdNotification(1L);
        when(notificationRepository.findById(notification.getIdNotification())).thenReturn(Optional.of(notification));

        Notification notificationUpdate = new Notification();
        notificationUpdate.setIdNotification(1L);
        notificationUpdate.setCategory("BOOKING");
        notificationUpdate.setTitle("Pembayaran");
        notificationUpdate.setMessage("Segera Lakukan Pembayaran");

        notificationService.updateNotification(notification);

        ArgumentCaptor<Notification> userEntityArgumentCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(userEntityArgumentCaptor.capture());

        Notification captureNotification = userEntityArgumentCaptor.getValue();
        assertThat(captureNotification).isEqualTo(notification);
    }

    @Test
    void deleteNotification(){
        Notification notification = new Notification();
        notification.setIdNotification(1L);
        when(notificationRepository.findById(notification.getIdNotification())).thenReturn(Optional.of(notification));

        notificationService.deleteNotification(1L);
        verify(notificationRepository).deleteById(1L);
    }
    @Test
    void getAllNotificationByUser(){
        notificationService.getAllNotificationByUser(1L);
        verify(notificationRepository).getNotificationByUser(1L);
    }
    @Test
    void getNotificationById(){
        notificationService.getNotificationyId(1L);
        verify(notificationRepository).findById(1L);
    }

    @Test
    void isRead(){
        notificationService.isRead(1L);
        verify(notificationRepository).isRead(1L);
    }
}
