package com.ironhack.greenTrack.controllers;


import com.ironhack.greenTrack.models.ToCycle;
import com.ironhack.greenTrack.repositories.ToCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ToCycleController {
    @Autowired
    private ToCycleRepository toCycleRepository;

    @PostMapping("/eco-action/create/to-cycle")
    @ResponseStatus(HttpStatus.CREATED)
    public ToCycle createToCycle(@RequestBody ToCycle toCycle) {
        return toCycleRepository.save(toCycle);
    }


}
