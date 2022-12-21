package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane,Long> {

}
