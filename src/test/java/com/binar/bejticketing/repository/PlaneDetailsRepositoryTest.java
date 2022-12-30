package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.PlaneDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlaneDetailsRepositoryTest {

    @Autowired
    private PlaneDetailsRepository planeDetailsRepository;

    private PlaneDetails planeDetails = new PlaneDetails();

    @AfterEach
    void tearDown() {
        planeDetailsRepository.deleteAll();
    }
    @Test
    public void findByNameBusiness(){
        planeDetails.setPlaneClass("BUSINESS");
        planeDetails.setPrice(100000l);
        planeDetailsRepository.save(planeDetails);
        assertThat(planeDetailsRepository.findByName("BUSINESS")).isNotNull();
    }

    @Test
    public void findByNameBusinessFalse(){
        assertThat(planeDetailsRepository.findByName("BUSINESS")).isNull();
    }

    @Test
    public void findByNameEconomy(){
        planeDetails.setPlaneClass("ECONOMY");
        planeDetails.setPrice(100000l);
        planeDetailsRepository.save(planeDetails);
        assertThat(planeDetailsRepository.findByName("ECONOMY")).isNotNull();
    }

    @Test
    public void findByNamEconomyFalse(){
        assertThat(planeDetailsRepository.findByName("ECONOMY")).isNull();
    }
}
