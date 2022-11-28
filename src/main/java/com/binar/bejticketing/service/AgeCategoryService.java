package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AgeCategoryService {
    AgeCategory createAgeCategory(AgeCategory ageCategory);
    List<AgeCategory> getAllAgeCategories();
    AgeCategory updateAgeCategory(AgeCategory ageCategory);
    DataNotFoundException deleteAgeCategory(Long id);
}
