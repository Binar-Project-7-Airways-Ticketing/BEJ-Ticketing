package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AgeCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.binar.bejticketing.utils.AgeCategoryName.CHILDREN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AgeCategoryRepositoryTest {
    @Autowired
    private AgeCategoryRepository ageCategoryRepository;
    private AgeCategory ageCategory;
    @AfterEach
    void tearDown() {
        ageCategoryRepository.deleteAll();
    }

    @Test
    void updateAgeCategory() {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ageCategory = AgeCategory.builder().idCategory(2L).nameCategory(CHILDREN).price(BigInteger.valueOf(10000)).createdAt(parse).updatedAt(null).build();
        ageCategoryRepository.save(ageCategory);
        AgeCategory ageCategory1 = ageCategoryRepository.updateAgeCategory(2L,"ADULT");
        assertThat(ageCategory1.getIdCategory()).isEqualTo(2L);
    }
}