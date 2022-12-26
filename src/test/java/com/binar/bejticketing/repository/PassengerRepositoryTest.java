package com.binar.bejticketing.repository;

import com.binar.bejticketing.BejTicketingApplication;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.utils.Gender;
import com.binar.bejticketing.utils.TitleUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.binar.bejticketing.utils.SpecialRequest.NONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
//@Transactional
//@SpringBootTest(classes = BejTicketingApplication.class)
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@DataJpaTest
class PassengerRepositoryTest {
    @Autowired
    private PassengerRepository passengerRepository;

    Passenger passenger = new Passenger();

    @BeforeEach
    void setUp(){
        Date parse = null;
        Date parseBirthday = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
            parseBirthday = new SimpleDateFormat("MM/dd/yyyy").parse("12/07/2001");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        passenger.setIdPassenger(1L);
        passenger.setPassport("0829102");
        passenger.setBirthday(parseBirthday);
        passenger.setTitleUser(TitleUser.MR);
        passenger.setDeleted(false);
        passenger.setContactNumber("08232368823");
        passenger.setFirstName("Fathan");
        passenger.setLastName("Azka");
        passenger.setGender(Gender.PRIA);
        passenger.setNationality("INDONESIA");
        passenger.setSpecialRequest(NONE);
        passenger.setCreatedAt(parse);
//        when(passengerRepository.save(passenger)).thenReturn(passenger);

        passengerRepository.saveAllAndFlush(List.of(passenger));
    }
    @AfterEach
    void tearUp(){
        passengerRepository.deleteAll();
    }

    @Test
    void getAllPassengers() {
        List<Passenger> passengers = passengerRepository.getAllPassengers();
        List<Passenger> passengers2 = new ArrayList<>();
        System.out.println(passengers);

//        when(passengerRepository.getAllPassengers()).thenReturn(passengers);
//        assertEquals(passengerRepository.getAllPassengers().size(),1);
//        passengers2.add(passenger);
//        assertEquals(passengers2.size(),passengers.size());
    }

    @Test
    void getPassengerById() {
        System.out.println(passenger.getIdPassenger());
        Passenger passenger1 = passengerRepository.getPassengerById(passenger.getIdPassenger());
        assertEquals(passenger.getIdPassenger(), passenger1.getIdPassenger());
    }

    @Test
    void deletePassenger() {
        passengerRepository.save(passenger);
        int result = passengerRepository.deletePassenger(passenger.getIdPassenger());
        System.out.println(result);
    }

    @Test
    void getPassengersByName() {
        passengerRepository.save(passenger);
        List<Passenger> result = passengerRepository.getPassengersByName(passenger.getFirstName());
        assertEquals(1, result.size());
    }
}