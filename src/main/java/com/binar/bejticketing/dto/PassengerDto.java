package com.binar.bejticketing.dto;

import javax.validation.constraints.Email;

public class PassengerDto {
    private Long id;

    private String firstName;
    private String lastName;

    @Email
    private String email;
}
