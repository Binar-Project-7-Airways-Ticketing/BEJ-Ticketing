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
