package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
