package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region
    ) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        else if (id != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryIdAndAge(id, age), HttpStatus.OK);
        }
        else if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegionIgnoreCase(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
