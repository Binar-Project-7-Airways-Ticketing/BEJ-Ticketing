package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.Luggage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LuggageService {
    List<Luggage> getAllLuggage();
    List<Luggage> getLuggageByIdPlane(Long idPlane);
    List<Luggage> getLuggageByState(boolean state);

    Luggage createLuggage(Luggage luggage);
    Luggage updateStateLuggage(Long id);
    Luggage updatePlaneDetailLuggage(Long idLuggage, Long idPlane);
}
