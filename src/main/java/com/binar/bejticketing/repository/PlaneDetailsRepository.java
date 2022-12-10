package com.binar.bejticketing.repository;


import com.binar.bejticketing.dto.PlaneClassEnum;
import com.binar.bejticketing.entity.PlaneDetails;
import com.binar.bejticketing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlaneDetailsRepository extends JpaRepository<PlaneDetails,Long> {
    @Query("SELECT p FROM PlaneDetails p WHERE p.planeClass= :planeClass")
    PlaneDetails findByName(String planeClass);
}
