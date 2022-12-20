package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Luggage;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.entity.Seat;
import com.binar.bejticketing.repository.LuggageRepository;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LuggageServiceImpl implements LuggageService {
    @Autowired
    private LuggageRepository luggageRepository;
    @Autowired
    private PlaneDetailsRepository planeDetailsRepository;
    @Override
    public List<Luggage> getAllLuggage() {
        return luggageRepository.findAll();
    }

    @Override
    public List<Luggage> getLuggageByIdPlane(Long idPlane) {
        Optional<PlaneDetails> planeDetails = planeDetailsRepository.findById(idPlane);
        if (planeDetails.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<Luggage> luggages = luggageRepository.findAll();
        List<Luggage> arraySeat = new ArrayList<>();
        luggages.forEach(luggage -> {
            Long idPlaneClass = luggage.getPlaneDetails().getIdPlaneClass();
            System.out.println(idPlaneClass);
            if (idPlaneClass.equals(idPlane)){
                arraySeat.add(luggage);
            }
        });
        return arraySeat;
    }

    @Override
    public List<Luggage> getLuggageByState(boolean state) {
        return luggageRepository.findLuggageByStateTrue(state);
    }

    @Override
    public Luggage createLuggage(Luggage luggage) {
        return luggageRepository.saveAndFlush(luggage);
    }

    @Override
    public Luggage updateStateLuggage(Long id) {
        return luggageRepository.updateLuggage(id);
    }

    @Override
    public Luggage updatePlaneDetailLuggage(Long idLuggage, Long idPlane) {
        Optional<PlaneDetails> planeDetails = planeDetailsRepository.findById(idPlane);
        Optional<Luggage> luggageCheck = luggageRepository.findById(idLuggage);

        if (planeDetails.isEmpty()){
            throw new EntityNotFoundException();
        }
        luggageCheck.get().setPlaneDetails(planeDetails.get());
        return luggageRepository.saveAndFlush(luggageCheck.get());
    }
}
