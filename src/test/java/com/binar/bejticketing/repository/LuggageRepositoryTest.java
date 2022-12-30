package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Luggage;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LuggageRepositoryTest {

    @Autowired
    private LuggageRepository luggageRepository;

    Luggage luggage = new Luggage();
    @BeforeEach
    void setUp(){

        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Luggage luggage = new Luggage();
        luggage.setIdLuggage(1L);
        luggage.setCapacity(BigInteger.valueOf(30));
        luggage.setPrice(BigInteger.valueOf(173000));
        luggage.setReady(true);
        luggage.setCreatedAt(parse);
        luggageRepository.save(luggage);
    }
    @AfterEach
    void tearDown(){
        luggageRepository.deleteAll();
    }
    @Test
    void findLuggageByStateTrue() {
        List<Luggage> luggages = luggageRepository.findLuggageByStateTrue(true);
        assertEquals(luggages.size() , 1);
    }


    @Test
    void updateLuggage() {
        int luggage1 = luggageRepository.updateLuggage(1L);
        assertEquals(luggage1, 1);
    }
}