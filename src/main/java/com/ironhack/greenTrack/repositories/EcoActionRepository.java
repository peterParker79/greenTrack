package com.ironhack.greenTrack.repositories;

import com.ironhack.greenTrack.models.EcoAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcoActionRepository extends JpaRepository<EcoAction, Integer> {
    @Override
    Optional<EcoAction> findById(Integer integer);
    Optional<EcoAction> findByDescription(String description);
}
