package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.PlaneRepository;
import com.binar.bejticketing.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    PlaneRepository planeRepository;

    @Override
    public Plane createPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    @Override
    public Plane updatePlane(Plane plane) {
        Optional<Plane> byId = planeRepository.findById(plane.getIdPlane());

        if (byId.isPresent()){
            return planeRepository.save(plane);
        }
        throw new DataNotFoundException(plane.getIdPlane());
    }

    @Override
    public DataNotFoundException deletePlane(Long id) {
        Optional<Plane> byId = planeRepository.findById(id);

        if (byId.isPresent()){
            planeRepository.deleteById(id);
            return null;
        }

        return new DataNotFoundException(id);
    }

    @Override
    public List<Plane> getAllPlane() {
        return planeRepository.findAll();
    }

    @Override
    public Optional<Plane> getPlaneById(Long id) {

        return planeRepository.findById(id);
    }
}
