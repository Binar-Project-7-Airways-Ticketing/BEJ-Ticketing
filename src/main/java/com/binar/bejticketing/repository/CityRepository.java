package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query("SELECT c FROM City c WHERE c.cityName = :name")
    City getCityByName(String name);
}
