package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Luggage;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.repository.LuggageRepository;
import com.binar.bejticketing.repository.PlaneDetailsRepository;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LuggageServiceImplTest {

    @Mock
    private LuggageRepository luggageRepository;
    @Mock private PlaneDetailsRepository planeDetailsRepository;
    @InjectMocks
    private LuggageServiceImpl underTest;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();
        Luggage luggage = new Luggage();
        PlaneDetails planeDetails = new PlaneDetails();
        PlaneDetails planeDetails2 = new PlaneDetails();
    @BeforeEach
    void setUp(){
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        underTest = new LuggageServiceImpl(luggageRepository, planeDetailsRepository);

        planeDetails.setIdPlaneClass(1L);
        planeDetails.setPlaneClass("ECONOMY");
        planeDetails.setPrice(10000L);

        when(planeDetailsRepository.findById(1L)).thenReturn(Optional.ofNullable(planeDetails));
        planeDetails2 = planeDetailsRepository.findById(1L).get();
        luggage.setIdLuggage(1L);
        luggage.setCapacity(BigInteger.valueOf(30));
        luggage.setPrice(BigInteger.valueOf(173000));
        luggage.setReady(false);
        luggage.setPlaneDetails(planeDetails);
        luggage.setCreatedAt(parse);
    }

    @AfterEach
    void tearUp(){
        luggageRepository.deleteAll();
        planeDetailsRepository.deleteAll();
    }

    @Test
    void getAllLuggage() {
        List<Luggage> luggages = new ArrayList<>();
        luggages.add(luggage);
        when(luggageRepository.findAll()).thenReturn(luggages);
        List<Luggage> luggages1 = underTest.getAllLuggage();
        assertEquals(luggages1.size(), 1);
    }

    @Test
    void getLuggageByIdPlane() {
        List<Luggage> luggages = new ArrayList<>();
        luggages.add(luggage);
        when(underTest.getLuggageByIdPlane(luggage.getPlaneDetails().getIdPlaneClass())).thenReturn(luggages);
        List<Luggage> luggages1 = underTest.getLuggageByIdPlane(luggage.getPlaneDetails().getIdPlaneClass());
        assertEquals(luggages1, luggages);
    }

    @Test
    void getLuggageByState() {
        List<Luggage> luggages = new ArrayList<>();
        luggages.add(luggage);
        when(underTest.getLuggageByState(true)).thenReturn(luggages);
        List<Luggage> luggages1 = underTest.getLuggageByState(true);
//        System.out.println(luggages1);
        assertEquals(luggages1, luggages);
    }

    @Test
    void createLuggage() {
        when(underTest.createLuggage(luggage)).thenReturn(luggage);
        Luggage luggage1 = underTest.createLuggage(luggage);
        verify(luggageRepository).save(luggage);
        assertEquals(luggage1.getIdLuggage(),luggage.getIdLuggage());
    }

    @Test
    void updateStateLuggage() {
        List<Luggage> luggages = new ArrayList<>();
        luggages.add(luggage);

        when(underTest.getLuggageByIdPlane(luggage.getPlaneDetails().getIdPlaneClass())).
                thenReturn(luggages);
        List<Luggage> luggages1 = underTest.getLuggageByIdPlane(luggage.getPlaneDetails().getIdPlaneClass());

        System.out.println(luggages1.size());

        given(luggageRepository.updateLuggage(luggage.getIdLuggage())).willReturn(1);

        int result = luggageRepository.updateLuggage(luggage.getIdLuggage());
        assertEquals(result, 1);
    }

    @Test
    void updateStateLuggageReturnFalse() {
        given(luggageRepository.findById(anyLong())).willReturn(Optional.empty());
        Throwable throwable = assertThrows(NoSuchFieldException.class, () -> underTest.updateStateLuggage(luggage.getIdLuggage()));
        assertEquals(throwable.getMessage(), "No value present");
    }

    @Test
    void updatePlaneDetailLuggage() {
        given(luggageRepository.findById(luggage.getIdLuggage())).willReturn(Optional.of(luggage));
        underTest.updatePlaneDetailLuggage(luggage.getIdLuggage(),luggage.getPlaneDetails().getIdPlaneClass());
        verify(luggageRepository).findById(luggage.getIdLuggage());
    }
}