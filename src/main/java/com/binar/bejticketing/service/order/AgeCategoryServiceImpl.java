package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.exception.DataAlreadyExistException;
import com.binar.bejticketing.exception.DataNotFoundException;
import com.binar.bejticketing.repository.AgeCategoryRepository;
import com.binar.bejticketing.service.AgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgeCategoryServiceImpl implements AgeCategoryService {
    @Autowired
    AgeCategoryRepository ageCategoryRepository;
    private boolean ageCategory1;

    @Override
    public AgeCategory createAgeCategory(AgeCategory ageCategory) {
        ageCategoryRepository.isCheckedByUsername(String.valueOf(ageCategory.getNameCategory()))
                .ifPresent(data ->
                        ageCategory1 = Objects.equals(data.getNameCategory(), ageCategory.getNameCategory())
                );
        System.out.println(ageCategory1);
        if (ageCategory1){
            System.out.println("Failed to Save");
            throw  new DataAlreadyExistException(ageCategory.getNameCategory());
        }
        return ageCategoryRepository.save(ageCategory);
    }

    @Override
    public List<AgeCategory> getAllAgeCategories() {
        return ageCategoryRepository.findAll();
    }

    @Override
    public AgeCategory updateAgeCategory(AgeCategory ageCategory) {
        var id = ageCategory.getIdCategory();
        Optional<AgeCategory> byId = ageCategoryRepository.findById(id);

        if (byId.isPresent()){
            return ageCategoryRepository.updateAgeCategory(ageCategory.getIdCategory(), String.valueOf(ageCategory.getNameCategory()));
        }
        throw new DataNotFoundException(id);
    }

    @Override
    public DataNotFoundException deleteAgeCategory(Long id) {
        Optional<AgeCategory> byId = ageCategoryRepository.findById(id);

        if (byId.isPresent()){
            ageCategoryRepository.deleteById(id);
            return null;
        }

        return new DataNotFoundException(id);
    }
}
