package com.binar.bejticketing.service.flight;

import com.binar.bejticketing.repository.AirportRepository;
import com.binar.bejticketing.service.Flight.AirportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AirportServiceImplTest {
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportServiceImpl airportService;

    @Test
    void createAirport(){

    }
}
