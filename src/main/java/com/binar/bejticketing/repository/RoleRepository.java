package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByroleStatus(String roleStatus);
}
