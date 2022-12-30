package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.entity.Passenger;
import com.binar.bejticketing.faker.MockData;
import com.binar.bejticketing.repository.AgeCategoryRepository;
import com.binar.bejticketing.repository.PassengerRepository;
import com.binar.bejticketing.utils.Gender;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.binar.bejticketing.utils.AgeCategoryName.ADULT;
import static com.binar.bejticketing.utils.AgeCategoryName.CHILDREN;
import static com.binar.bejticketing.utils.SpecialRequest.NONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PassengerServiceImplTest {

    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private AgeCategoryRepository ageCategoryRepository;

    @InjectMocks PassengerServiceImpl passengerService;

    Passenger passenger = new Passenger();
    AgeCategory ageCategory = new AgeCategory();
    List<AgeCategory> ageCategories = new ArrayList<>();
    @BeforeEach
    void setUp(){

        MockData mockData = new MockData();
        ageCategory = mockData.mockDataAgeCategory(ageCategory);
        passenger = mockData.mockDataPassenger(passenger);
        ageCategories.add(ageCategory);
        when(ageCategoryRepository.findAll()).thenReturn(ageCategories);
        ageCategories = ageCategoryRepository.findAll();

    }
    @Test
    void createPassenger() {
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        Passenger passenger1 = passengerService.createPassenger(passenger);
        assertEquals(passenger1.getIdPassenger(),passenger.getIdPassenger());
    }

    @Test
    void createPassengerReturnFalse() {
        when(passengerRepository.save(nullable(Passenger.class))).thenReturn(isNull());
        Passenger passenger1 = passengerService.createPassenger(null);
        assertNull(passenger1);
    }

//    @Test
//    void createPassengerInputDateFalse() {
//        Date parseBirthday = null;
//        try {
//            parseBirthday = new SimpleDateFormat("MM-dd-yyyy").parse("12/07/2001");
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
////        when(passengerRepository.save(passenger)).thenReturn(passenger).thenReturn(assertThrows(new ParseException()));
////        Passenger passenger1 = passengerService.createPassenger(null);
////        assertNull(passenger1);
//    }

    @Test
    void createPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        when(passengerRepository.saveAllAndFlush(passengers)).thenReturn(passengers);
        List<Passenger> passengers1 = passengerService.createPassengers(passengers);
        assertEquals(passengers1.size(), 1);
    }

    @Test
    void getPassengerById() {
        when(passengerRepository.getPassengerById(passenger.getIdPassenger())).thenReturn(passenger);
        Passenger passenger1 = passengerService.getPassengerById(passenger.getIdPassenger());
        assertEquals(passenger1.getIdPassenger(), passenger1.getIdPassenger());
    }

    @Test
    void getPassengersByName() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        when(passengerRepository.getPassengersByName(passenger.getFirstName())).thenReturn(passengers);
        List<Passenger> passengers1 = passengerService.getPassengersByName(passenger.getFirstName());
        assertEquals(passengers1.size(), passengers.size());
    }

    @Test
    void getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        when(passengerRepository.getAllPassengers()).thenReturn(passengers);
        List<Passenger> passengers1 = passengerService.getAllPassengers();
        assertEquals(passengers1.size(), passengers.size());

    }

    @Test
    void deleteDataPassenger() {
        when(passengerRepository.getPassengerById(passenger.getIdPassenger())).thenReturn(passenger);
        Passenger passenger1 = passengerService.getPassengerById(passenger.getIdPassenger());
        when(passengerRepository.findById(passenger1.getIdPassenger())).thenReturn(Optional.of(passenger));
        when(passengerRepository.deletePassenger(passenger.getIdPassenger())).thenReturn(1);
//        System.out.println(passengerService.deleteDataPassenger(passenger.getIdPassenger()));
        String result = passengerService.deleteDataPassenger(passenger1.getIdPassenger());
        System.out.println(result);
        assertEquals("Success", result);
    }

    @Test
    void updatePassenger() {
        when(passengerRepository.findById(passenger.getIdPassenger())).thenReturn(Optional.of(passenger));

        when(passengerRepository.save(passenger)).thenReturn(passenger);
        Passenger passenger1 = passengerService.updatePassenger(passenger);
        assertEquals(passenger, passenger1);
    }

    @Test
    void updateAgeCategoryInPassenger() {
        when(passengerRepository.findById(passenger.getIdPassenger())).thenReturn(Optional.of(passenger));
        Long idCategory = ageCategories.get(0).getIdCategory();
        when(ageCategoryRepository.findById(idCategory)).thenReturn(Optional.ofNullable(ageCategories.get(0)));
        when(passengerRepository.saveAndFlush(passenger)).thenReturn(passenger);
        Passenger passenger1 = passengerService.updateAgeCategoryInPassenger(passenger.getIdPassenger(),idCategory);

        assertEquals(passenger, passenger1);
    }
}