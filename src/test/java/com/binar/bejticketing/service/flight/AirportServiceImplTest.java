package com.binar.bejticketing.service.flight;

import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.repository.AirportRepository;
import com.binar.bejticketing.service.Flight.AirportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AirportServiceImplTest {
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportServiceImpl airportService;

    @Test
    void createAirport(){
        Airport airport = new Airport();
        airport.setAirportCode("JKT");
        airport.setAirportName("Bandara");
        airportService.createAirport(airport);

        ArgumentCaptor<Airport> userEntityArgumentCaptor = ArgumentCaptor.forClass(Airport.class);
        verify(airportRepository).save(userEntityArgumentCaptor.capture());

        Airport captureAirport = userEntityArgumentCaptor.getValue();
        assertThat(captureAirport).isEqualTo(airport);

    }

    @Test
    void updateAirport(){
        Airport airport = new Airport();
        airport.setIdAirport(1L);
        when(airportRepository.findById(airport.getIdAirport())).thenReturn(Optional.of(airport));

        Airport airportUpdate = new Airport();
        airportUpdate.setIdAirport(1L);
        airportUpdate.setAirportCode("JKT");
        airportUpdate.setAirportName("Bandara");
        airportService.updateAirport(airport);

        ArgumentCaptor<Airport> userEntityArgumentCaptor = ArgumentCaptor.forClass(Airport.class);
        verify(airportRepository).save(userEntityArgumentCaptor.capture());

        Airport captureAirport = userEntityArgumentCaptor.getValue();
        assertThat(captureAirport).isEqualTo(airport);

    }
    @Test
    void deleteAirport(){
        Airport airport = new Airport();
        airport.setAirportCode("JKT");
        when(airportRepository.getAirportFromCode(airport.getAirportCode())).thenReturn(Optional.of(airport));
        airportService.deleteAirport("JKT");
        verify(airportRepository).deleteByAirportCode("JKT");
    }

    @Test
    void findAirportByName(){
        airportService.findAirportByName("Bandara");
        verify(airportRepository).getAirportFromName("Bandara");
    }

    @Test
    void findAirportByCity(){
        airportService.findAirportByCity("Jakarta");
        verify(airportRepository).getAirportFromCity("Jakarta");
    }
    @Test
    void findAirportByCode(){
        airportService.findAirportByCode("JKT");
        verify(airportRepository).getAirportFromCode("JKT");
    }
    @Test
    void findAllAirport(){
        airportService.findAllAirport();
        verify(airportRepository).findAll();
    }

    @Test
    void setImage(){
        airportService.setImage("https//image.com","JKT");
        verify(airportRepository).uploadImage("https//image.com","JKT");
    }
}
