package com.binar.bejticketing.dto;

import com.binar.bejticketing.utils.SeatUtils;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SeatResponseDto {
    private Long id;
    @NotEmpty(message = "Number Seat Don't empty")
    private String numberSeat;
    @NotEmpty(message = "State Seat Don't empty")
    private SeatUtils stateSeat;
}
