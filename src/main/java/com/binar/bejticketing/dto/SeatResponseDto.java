package com.binar.bejticketing.dto;

import com.binar.bejticketing.utils.SeatUtils;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SeatResponseDto {
    @NotEmpty(message = "Number Seat Don't empty")
    private String numberSeat;
    private SeatUtils stateSeat;
}
