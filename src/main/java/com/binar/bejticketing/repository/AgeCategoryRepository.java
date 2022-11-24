package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
    @Query("SELECT a FROM AgeCategory a WHERE a.nameCategory = :name")
    Optional<AgeCategory> isCheckedByUsername(String username);
}
