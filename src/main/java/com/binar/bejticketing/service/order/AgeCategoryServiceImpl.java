package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.repository.AgeCategoryRepository;
import com.binar.bejticketing.service.AgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgeCategoryServiceImpl implements AgeCategoryService {
    @Autowired
    AgeCategoryRepository ageCategoryRepository;
    @Override
    public AgeCategory createAgeCategory(AgeCategory ageCategory) {
        return ageCategoryRepository.save(ageCategory);
    }

    @Override
    public List<AgeCategory> getAllAgeCategories() {
        return ageCategoryRepository.findAll();
    }
}
