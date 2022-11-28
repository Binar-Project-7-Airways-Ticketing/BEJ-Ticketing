package com.binar.bejticketing.service.Flight;

import com.binar.bejticketing.repository.CityRepository;
import com.binar.bejticketing.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import com.binar.bejticketing.entity.City;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.getCityByName(name);
    }
}
