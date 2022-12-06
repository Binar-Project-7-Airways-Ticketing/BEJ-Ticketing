package com.binar.bejticketing.dto;

import com.binar.bejticketing.utils.SeatUtils;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Getter
@Setter
public class LuggageDto {
    private BigInteger capacity;
    private BigInteger price;
}
