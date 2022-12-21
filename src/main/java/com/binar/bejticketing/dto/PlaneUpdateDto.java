package com.binar.bejticketing.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaneUpdateDto {
    private Long idPlane;
    private String planeType;
    private Integer baggageCapacity;
}
