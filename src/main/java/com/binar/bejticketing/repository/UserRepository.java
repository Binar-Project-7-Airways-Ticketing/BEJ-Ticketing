package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.isActive = false WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.pictureUrl =:url WHERE u.id = :id")
    void uploadImage(String url , Long id);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password =:password WHERE u.id = :id")
    void changePassword(String password , Long id);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isActive=true")
    User getUserById(Long id);

//    @Transactional(readOnly = true)
    @Modifying
    @Query("UPDATE User u SET u.isActive = false where u.lastLoginDate <:date")
    void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);
    User findByDisplayName(String displayName);

    User findByEmail(String email);
    Boolean existsByDisplayName(String displayName);
    Boolean existsByEmail(String email);
}
