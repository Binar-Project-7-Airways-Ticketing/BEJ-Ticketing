package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.repository.AgeCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.binar.bejticketing.utils.AgeCategoryName.CHILDREN;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AgeCategoryServiceImplTest {

    @Mock
    private AgeCategoryRepository ageCategoryRepository;
    private AgeCategoryServiceImpl ageCategoryService;
    private AgeCategory ageCategory;

    @BeforeEach
    private void setUp(){
        ageCategoryService = new AgeCategoryServiceImpl(ageCategoryRepository);
    }
    @Test
    void createAgeCategory() {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ageCategory = AgeCategory.builder().idCategory(2L).nameCategory(CHILDREN).price(BigInteger.valueOf(10000)).createdAt(parse).updatedAt(null).build();

        ageCategoryService.createAgeCategory(ageCategory);
        verify(ageCategoryRepository).save(ageCategory);
    }

    @Test
    void getAllAgeCategories() {
        ageCategoryService.getAllAgeCategories();
        verify(ageCategoryRepository).findAll();
    }

    @Test
    void updateAgeCategory() {
        long id = 1;
        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ageCategory = AgeCategory.builder().idCategory(id).nameCategory(CHILDREN).price(BigInteger.valueOf(10000)).createdAt(parse).updatedAt(null).build();

        given(ageCategoryRepository.findById(id)).isPresent();
        ageCategoryService.updateAgeCategory(ageCategory);
        verify(ageCategoryRepository).save(ageCategory);
    }

    @Test
    void deleteAgeCategory() {
        long id = 1;
        given(ageCategoryRepository.findById(id)).isPresent();
        ageCategoryService.deleteAgeCategory(id);
        verify(ageCategoryRepository).deleteById(id);
    }
}