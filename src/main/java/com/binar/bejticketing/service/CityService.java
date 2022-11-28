package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.City;
import org.springframework.stereotype.Service;

@Service
public interface CityService {
    City createCity(City city);
    City getCityByName(String name);
}
