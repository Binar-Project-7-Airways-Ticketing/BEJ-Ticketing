package com.binar.bejticketing.service.flight;

import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import com.binar.bejticketing.service.Flight.PlaneDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PlaneDetailsServiceImplTest {
    @Mock
    private PlaneDetailsRepository planeDetailsRepository;

    @InjectMocks
    private PlaneDetailsServiceImpl planeDetailsService;

    @Test
    void createPlaneDetailsBusiness(){
        PlaneDetails planeDetails = new PlaneDetails();
        planeDetails.setPlaneClass("BUSINESS");
        planeDetails.setPrice(100000L);
        planeDetailsService.createPlaneDetails(planeDetails);

        ArgumentCaptor<PlaneDetails> userEntityArgumentCaptor = ArgumentCaptor.forClass(PlaneDetails.class);
        verify(planeDetailsRepository).save(userEntityArgumentCaptor.capture());

        PlaneDetails capturePlaneDetails = userEntityArgumentCaptor.getValue();
        assertThat(capturePlaneDetails).isEqualTo(planeDetails);
    }

    @Test
    void createPlaneDetailsEconomy(){
        PlaneDetails planeDetails = new PlaneDetails();
        planeDetails.setPlaneClass("ECONOMY");
        planeDetails.setPrice(0L);
        planeDetailsService.createPlaneDetails(planeDetails);

        ArgumentCaptor<PlaneDetails> userEntityArgumentCaptor = ArgumentCaptor.forClass(PlaneDetails.class);
        verify(planeDetailsRepository).save(userEntityArgumentCaptor.capture());

        PlaneDetails capturePlaneDetails = userEntityArgumentCaptor.getValue();
        assertThat(capturePlaneDetails).isEqualTo(planeDetails);
    }

    @Test
    void updatePlaneDetails(){
        PlaneDetails planeDetails = new PlaneDetails();
        planeDetails.setIdPlaneClass(1L);
        when(planeDetailsRepository.findById(planeDetails.getIdPlaneClass())).thenReturn(Optional.of(planeDetails));

        PlaneDetails planeDetailsUpdate = new PlaneDetails();
        planeDetailsUpdate.setIdPlaneClass(1L);
        planeDetailsUpdate.setPlaneClass("ECONOMY");
        planeDetailsUpdate.setPrice(200000L);
        planeDetailsService.updatePlaneDetails(planeDetailsUpdate);

        ArgumentCaptor<PlaneDetails> userEntityArgumentCaptor = ArgumentCaptor.forClass(PlaneDetails.class);
        verify(planeDetailsRepository).save(userEntityArgumentCaptor.capture());

        PlaneDetails capturePlaneDetails = userEntityArgumentCaptor.getValue();
        assertThat(capturePlaneDetails).isEqualTo(planeDetailsUpdate);
    }

    @Test
    void deletePlaneDetails(){
        PlaneDetails planeDetails = new PlaneDetails();
        planeDetails.setIdPlaneClass(1L);
        when(planeDetailsRepository.findById(planeDetails.getIdPlaneClass())).thenReturn(Optional.of(planeDetails));
        planeDetailsService.deletePlaneDetails(1L);
        verify(planeDetailsRepository).deleteById(1L);
    }

    @Test
    void getAllPlaneDetails(){
        planeDetailsService.getAllPlaneDetails();
        verify(planeDetailsRepository).findAll();
    }

    @Test
    void findPlaneDetailsByName(){
        planeDetailsService.findByName("BUSINESS");
        verify(planeDetailsRepository).findByName("BUSINESS");
    }

}
