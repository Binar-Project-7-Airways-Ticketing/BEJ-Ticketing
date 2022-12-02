package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    @Query("SELECT c FROM Airport c WHERE c.airportCode = :code")
    Optional<Airport> getAirportFromCode(String code);

    @Modifying
    @Transactional
    @Query("UPDATE Airport a SET a.urlPhoto =:url WHERE a.city = :city")
    void uploadImage(String url , String city);

    @Query("SELECT a FROM Airport a WHERE a.airportName = :name")
    Airport getAirportFromName(String name);

    @Query("SELECT c FROM Airport c WHERE c.city = :name")
    List<Airport> getAirportFromCity(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Airport a WHERE a.airportCode = :code")
    void deleteByAirportCode(String code);


}
