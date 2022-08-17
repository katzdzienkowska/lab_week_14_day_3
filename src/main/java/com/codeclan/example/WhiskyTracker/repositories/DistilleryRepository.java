package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistilleryRepository extends JpaRepository<Distillery, Long> {

    // Get all the distilleries for a particular region
    List<Distillery> findByRegionIgnoreCase(String region);

    // Get distilleries that have whiskies that are 12 years old
    List<Distillery> findByWhiskiesAgeEquals(int age);

}
