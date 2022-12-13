package com.binar.bejticketing.dto;

import com.binar.bejticketing.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.Date;

@Setter
@Getter
public class UserUpdateDto {

    private String displayName;

    private String firstName;

    private String lastName;

    private Gender gender;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    @Email(message = "Value must be Email")
    private String email;


}
