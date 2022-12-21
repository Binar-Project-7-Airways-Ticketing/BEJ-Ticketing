package com.binar.bejticketing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FlightUpdateDTO {
    private Long idFlight;
    private String flightNumber;
    private String departureCode;
    private String arrivalCode;


    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date departureDate;


    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date arrivalDate;


    @JsonFormat(pattern = "hh:mm")
    private Date departureTime;


    @JsonFormat(pattern = "hh:mm")
    private Date arrivalTime;
    private Long plane;
    private Long price;
}
