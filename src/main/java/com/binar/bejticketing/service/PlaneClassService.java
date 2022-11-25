package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.PlaneClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaneClassService {
    PlaneClass createPlaneClass(PlaneClass planeClass);

    List<PlaneClass> getAllPlaneClass();
}
