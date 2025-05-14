package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.ToCycle;
import com.ironhack.greenTrack.repositories.ToCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ToCycleController {
    @Autowired
    private ToCycleRepository toCycleRepository;

    @PostMapping("/eco-action/create/to-cycle")

    public ResponseEntity<String> createToCycle() {
        ToCycle toCycle = new ToCycle();
        toCycleRepository.save(toCycle);
        return ResponseEntity.status(HttpStatus.CREATED).body("Eco Action 'To Cycle' has been created.\n " +
                "Users can record this activity through \n\tapi/profile/id_profile/new-ecoaction/to-cycle\n" +
                "This action require fill date, description, kilometers, origin and destination");

    }





}
