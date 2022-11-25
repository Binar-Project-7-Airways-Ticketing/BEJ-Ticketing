package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AgeCategory;
import com.binar.bejticketing.service.AgeCategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
    @Query("SELECT a FROM AgeCategory a WHERE a.nameCategory = :username")
    Optional<AgeCategory> isCheckedByUsername(String username);

    @Modifying
    @Query("UPDATE AgeCategory a SET a.nameCategory = :username WHERE a.idCategory = :id")
    AgeCategory updateAgeCategory(Long id,String username);
}
