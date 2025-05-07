package com.ironhack.greenTrack.services;

import com.ironhack.greenTrack.models.EcoAction;
import com.ironhack.greenTrack.repositories.EcoActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcoActionService {

    @Autowired
    private EcoActionRepository ecoActionRepository;

    List<EcoAction> getByDate(String date) {
        return ecoActionRepository.getEcoActionsByDate(date);
    }

    List <EcoAction> getByGreenImpact(int greenImpactValue) {
        return ecoActionRepository.findEcoActionByGreenImpact(greenImpactValue);
    }


}
