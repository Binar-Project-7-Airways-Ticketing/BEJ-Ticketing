package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
//    @Query("SELECT a FROM AgeCategory a WHERE a.")
}
