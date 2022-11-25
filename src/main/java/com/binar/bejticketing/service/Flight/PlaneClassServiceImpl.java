package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.PlaneClass;
import com.binar.bejticketing.repository.PlaneClassRepository;
import com.binar.bejticketing.service.PlaneClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneClassServiceImpl implements PlaneClassService {

    @Autowired
    PlaneClassRepository planeClassRepository;

    @Override
    public PlaneClass createPlaneClass(PlaneClass planeClass) {
        return planeClassRepository.save(planeClass);
    }

    @Override
    public List<PlaneClass> getAllPlaneClass() {
        return planeClassRepository.findAll();
    }
}
