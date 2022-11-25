package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.repository.AgeCategoryRepository;
import com.binar.bejticketing.service.AgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgeCategoryServiceImpl implements AgeCategoryService {
    @Autowired
    AgeCategoryRepository ageCategoryRepository;
    private Optional<AgeCategory> ageCategory1;

    @Override
    public AgeCategory createAgeCategory(AgeCategory ageCategory) {

        List<AgeCategory> ageCategoryList = ageCategoryRepository.findAll();
        System.out.println(ageCategoryList);
//        for (AgeCategory category : ageCategoryList) {
//            ageCategory1 = ageCategoryRepository.isCheckedByUsername(category.getNameCategory());
//        }
//        if (ageCategory1.isPresent()){
//            System.out.println("Failed to Save");
//            return null;
//        }
        return ageCategoryRepository.save(ageCategory);
    }

    @Override
    public List<AgeCategory> getAllAgeCategories() {
        return ageCategoryRepository.findAll();
    }
}
