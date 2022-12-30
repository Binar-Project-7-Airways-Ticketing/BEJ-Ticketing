package com.binar.bejticketing.service.flight;

import com.binar.bejticketing.entity.Flight;
import com.binar.bejticketing.entity.Notification;
import com.binar.bejticketing.repository.FlightRepository;
import com.binar.bejticketing.service.Flight.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    void createFlight(){
        Flight flight = new Flight();
        flight.setFlightNumber("SOS 1");
        flight.setDepartureCode("JKT");
        flight.setArrivalCode("DPS");



        flightService.createFlight(flight);
        ArgumentCaptor<Flight> userEntityArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
        verify(flightRepository).save(userEntityArgumentCaptor.capture());

        Flight captureFlight = userEntityArgumentCaptor.getValue();
        assertThat(captureFlight).isEqualTo(flight);
    }

    @Test
    void updateFlight(){
        Flight flight = new Flight();
        flight.setIdFlight(1L);
        when(flightRepository.findById(flight.getIdFlight())).thenReturn(Optional.of(flight));
        Flight flightUpdate = new Flight();
        flightUpdate.setIdFlight(1L);
        flightUpdate.setFlightNumber("SOS 1");
        flightUpdate.setDepartureCode("JKT");
        flightUpdate.setArrivalCode("DPS");
        flightService.updateFlight(flightUpdate);
        ArgumentCaptor<Flight> userEntityArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
        verify(flightRepository).save(userEntityArgumentCaptor.capture());

        Flight captureFlight = userEntityArgumentCaptor.getValue();
        assertThat(captureFlight).isEqualTo(flightUpdate);
    }

    @Test
    void deleteFlight(){
        Flight flight = new Flight();
        flight.setIdFlight(1L);
        when(flightRepository.findById(flight.getIdFlight())).thenReturn(Optional.of(flight));
        flightService.deleteFlight(1L);
        verify(flightRepository).deleteFlightById(1L);
    }

    @Test
    void findAllFlightAvailable(){
        flightService.findAllFlightAvailable();
        verify(flightRepository).getAllFlight();
    }

    @Test
    void findFlightSearch(){
        flightService.findFlightSearch("JKT","DPS");
        verify(flightRepository).getFlightSearch("JKT","DPS");
    }

    @Test
    void findFlightById(){
        flightService.findFlightById(1L);
        verify(flightRepository).findById(1L);
    }

    @Test
    void findFlightSearchDate(){
        flightService.findFlightSearchDate("JKT","DPS",new Date("12/20/2022"));
        verify(flightRepository).getFlightSearchDate("JKT","DPS",new Date("12/20/2022"));
    }

    @Test
    void findFlightSearchDatePaging(){

        Pageable pageable = PageRequest.of(0,2);
        flightService.findFlightSearchDatePaging("JKT","DPS",new Date(2022,12,30),pageable);
        verify(flightRepository).getFlightSearchDatePaging("JKT","DPS",new Date(2022,12,30),pageable);
    }
}
