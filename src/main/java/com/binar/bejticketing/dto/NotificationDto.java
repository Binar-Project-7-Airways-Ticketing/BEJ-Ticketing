package com.binar.bejticketing.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class NotificationDto {
    private Long user;
    private String title;
    private String message;
    private String category;

}
