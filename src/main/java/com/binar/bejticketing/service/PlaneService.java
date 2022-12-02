package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaneService {

    Plane createPlane(Plane plane);
    Plane updatePlane(Plane plane);
    DataNotFoundException deletePlane(Long id);

    List<Plane> getAllPlane();

    Optional<Plane> getPlaneById(Long id);
}
