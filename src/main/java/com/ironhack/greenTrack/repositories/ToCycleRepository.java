package com.ironhack.greenTrack.repositories;

import com.ironhack.greenTrack.models.ToCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToCycleRepository extends JpaRepository<ToCycle, Integer> {

}
