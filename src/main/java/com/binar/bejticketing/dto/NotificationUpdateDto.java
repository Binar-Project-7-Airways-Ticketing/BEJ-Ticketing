package com.binar.bejticketing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationUpdateDto {
    private Long idNotification;
    private Long user;
    private String title;
    private String message;
    private String category;
}
