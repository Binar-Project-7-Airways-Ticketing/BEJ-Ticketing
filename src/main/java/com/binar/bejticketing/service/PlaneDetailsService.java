package com.binar.bejticketing.service;


import com.binar.bejticketing.entity.PlaneDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaneDetailsService {
    PlaneDetails createPlaneDetails(PlaneDetails planeDetails);

    PlaneDetails updatePlaneDetails(PlaneDetails planeDetails);

    void deletePlaneDetails(Long id);

    List<PlaneDetails> getAllPlaneDetails();
}
