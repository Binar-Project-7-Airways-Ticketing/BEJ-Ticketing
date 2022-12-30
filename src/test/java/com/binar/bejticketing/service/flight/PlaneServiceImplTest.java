package com.binar.bejticketing.service.flight;

import com.binar.bejticketing.entity.Plane;
import com.binar.bejticketing.repository.PlaneRepository;
import com.binar.bejticketing.service.Flight.PlaneServiceImpl;
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
public class PlaneServiceImplTest {

    @Mock
    private PlaneRepository planeRepository;
    @InjectMocks
    private PlaneServiceImpl planeService;

    @Test
    void createPlane(){
        Plane plane = new Plane();
        plane.setPlaneType("BOEING 101");
        plane.setBaggageCapacity(1000);
        planeService.createPlane(plane);

        ArgumentCaptor<Plane> userEntityArgumentCaptor = ArgumentCaptor.forClass(Plane.class);
        verify(planeRepository).save(userEntityArgumentCaptor.capture());

        Plane capturePlane = userEntityArgumentCaptor.getValue();
        assertThat(capturePlane).isEqualTo(plane);
    }
    @Test
    void updatePlane(){
        Plane plane = new Plane();
        plane.setIdPlane(1L);
        when(planeRepository.findById(plane.getIdPlane())).thenReturn(Optional.of(plane));

        Plane planeUpdate = new Plane();
        planeUpdate.setIdPlane(1L);
        planeUpdate.setPlaneType("BOEING 101");
        planeUpdate.setBaggageCapacity(1000);
        planeService.updatePlane(planeUpdate);

        ArgumentCaptor<Plane> userEntityArgumentCaptor = ArgumentCaptor.forClass(Plane.class);
        verify(planeRepository).save(userEntityArgumentCaptor.capture());

        Plane capturePlane = userEntityArgumentCaptor.getValue();
        assertThat(capturePlane).isEqualTo(planeUpdate);
    }

    @Test
    void deletePlane(){
        Plane plane = new Plane();
        plane.setIdPlane(1L);
        when(planeRepository.findById(plane.getIdPlane())).thenReturn(Optional.of(plane));
        planeService.deletePlane(1L);
        verify(planeRepository).deleteById(1L);
    }

    @Test
    void getAllPlane(){
        planeService.getAllPlane();
        verify(planeRepository).findAll();
    }

    @Test
    void getPlaneById(){
        Plane plane = new Plane();
        plane.setIdPlane(1L);
        when(planeRepository.findById(plane.getIdPlane())).thenReturn(Optional.of(plane));
        planeService.getPlaneById(1L);
        verify(planeRepository).findById(1L);
    }
}
