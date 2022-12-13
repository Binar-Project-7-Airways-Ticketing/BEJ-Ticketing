package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Luggage;
import com.binar.bejticketing.repository.LuggageRepository;
import com.binar.bejticketing.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageServiceImpl implements LuggageService {
    @Autowired
    private LuggageRepository luggageRepository;
    @Override
    public List<Luggage> getAllLuggage() {
        return luggageRepository.findAll();
    }

    @Override
    public List<Luggage> getLuggageByIdPlane(Long idPlane) {
        return luggageRepository.findLuggageByIdPlane(idPlane);
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
}
