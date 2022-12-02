package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.roleStatus = :roleStatus")
    Role findByroleStatus(String roleStatus);
}
