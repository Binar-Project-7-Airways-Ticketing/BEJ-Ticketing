package com.binar.bejticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketDto {
    private Long idFlight;
    private String firstName;
    private String numberSeat;
    private Date departureDate;
    private Date departureTime;
    private String departureCode;
    private String arrivalCode;
}
