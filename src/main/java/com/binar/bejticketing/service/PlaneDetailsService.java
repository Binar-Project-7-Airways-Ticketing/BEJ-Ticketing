package com.binar.bejticketing.service;


import com.binar.bejticketing.entity.PlaneDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaneDetailsService {
    PlaneDetails createPlaneDetails(PlaneDetails planeDetails);
    PlaneDetails createPlaneClass(PlaneDetails planeClass);

    List<PlaneDetails> getAllPlaneClass();
}
