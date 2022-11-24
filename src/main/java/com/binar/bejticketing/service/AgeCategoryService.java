package com.binar.bejticketing.service;

import com.binar.bejticketing.entity.AgeCategory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AgeCategoryService {
    AgeCategory createAgeCategory(AgeCategory ageCategory);
    List<AgeCategory> getAllAgeCategories();
}
