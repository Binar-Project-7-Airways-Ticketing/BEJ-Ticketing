package com.binar.bejticketing.service.Flight;


import com.binar.bejticketing.dto.PlaneClassEnum;
import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.service.PlaneDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneDetailsServiceImpl implements PlaneDetailsService {

    @Autowired
    PlaneDetailsRepository planeDetailsRepository;

    @Override
    public PlaneDetails findByName(String planeClassEnum) {
        return planeDetailsRepository.findByName(planeClassEnum);
    }

    @Override
    public PlaneDetails createPlaneDetails(PlaneDetails planeDetails) {
        return planeDetailsRepository.save(planeDetails);
    }

    @Override
    public PlaneDetails updatePlaneDetails(PlaneDetails planeDetails) {
        Optional<PlaneDetails> byId = planeDetailsRepository.findById(planeDetails.getIdPlaneClass());

        if (byId.isPresent()){
            return planeDetailsRepository.save(planeDetails);
        }
        throw new DataNotFoundException(planeDetails.getIdPlaneClass());
    }

    @Override
    public String deletePlaneDetails(Long id) {
        Optional<PlaneDetails> byId = planeDetailsRepository.findById(id);

        if (byId.isPresent()){
            planeDetailsRepository.deleteById(id);
            return "Delete Success";
        }

        throw  new DataNotFoundException(id);


    }


    @Override
    public List<PlaneDetails> getAllPlaneDetails() {
        return planeDetailsRepository.findAll();
    }
}
