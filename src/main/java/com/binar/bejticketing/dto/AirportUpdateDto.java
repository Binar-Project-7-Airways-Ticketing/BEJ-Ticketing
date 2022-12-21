package com.binar.bejticketing.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirportUpdateDto {
    private Long idAirport;
    private String airportCode;
    private String airportName;
    private String city;
}
