package com.binar.bejticketing.dto;

import com.binar.bejticketing.entity.Plane;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Getter
@Setter
public class FlightDto {
    private String flightNumber;
    private String departureCode;
    private String arrivalCode;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date departureDate;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date arrivalDate;

    @DateTimeFormat(pattern="hh:mm")
    @JsonFormat(pattern = "hh:mm")
    private Date departureTime;

    @DateTimeFormat(pattern="hh:mm")
    @JsonFormat(pattern = "hh:mm")
    private Date arrivalTime;
    private Long plane;
    private Long price;
}
