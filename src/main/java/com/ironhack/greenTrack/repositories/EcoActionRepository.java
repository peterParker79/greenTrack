package com.ironhack.greenTrack.repositories;

import com.ironhack.greenTrack.models.EcoAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EcoActionRepository extends  JpaRepository<EcoAction, Integer> {
    //filtrar por fecha
        List <EcoAction>getEcoActionsByDate(String date);

    //filtrar por impacto
        List<EcoAction> findEcoActionByGreenImpact(int impactValue);



        List<EcoAction> findAll();

}
