package com.binar.bejticketing.service.Flight;


import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.service.PlaneDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneDetailsServiceImpl implements PlaneDetailsService {

    @Autowired
    PlaneDetailsRepository planeDetailsRepository;

    @Override
    public PlaneDetails createPlaneDetails(PlaneDetails planeDetails) {
        return planeDetailsRepository.save(planeDetails);
    }

    @Override
    public PlaneDetails createPlaneClass(PlaneDetails planeClass) {
        return planeDetailsRepository.save(planeClass);
    }

    @Override
    public List<PlaneDetails> getAllPlaneClass() {
        return planeDetailsRepository.findAll();
    }
}
