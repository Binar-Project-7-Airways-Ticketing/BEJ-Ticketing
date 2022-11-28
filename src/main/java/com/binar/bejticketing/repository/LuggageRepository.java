package com.binar.bejticketing.repository;

import com.binar.bejticketing.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LuggageRepository extends JpaRepository<Luggage, Long> {
    @Query("SELECT l FROM Luggage l WHERE l.isReady = :state")
    List<Luggage> findLuggageByStateTrue(boolean state);

    @Transactional
    @Modifying
    @Query("UPDATE Luggage l SET l.isReady = false WHERE l.idLuggage = :idLuggage")
    Luggage updateLuggage(Long idLuggage);
}
