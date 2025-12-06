package com.example.appdevf2.paraderooct17.Controller;

import com.example.appdevf2.paraderooct17.Entity.AvailabilityEntity;
import com.example.appdevf2.paraderooct17.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability") // This matches the URL in the test data
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public List<AvailabilityEntity> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public AvailabilityEntity getAvailabilityById(@PathVariable int id) {
        return availabilityService.getAvailabilityById(id);
    }

    @PostMapping
    public AvailabilityEntity createAvailability(@RequestBody AvailabilityEntity availability) {
        return availabilityService.saveAvailability(availability);
    }

    @PutMapping("/{id}")
    public AvailabilityEntity updateAvailability(@PathVariable int id, @RequestBody AvailabilityEntity availability) {
        return availabilityService.updateAvailability(id, availability);
    }

    @DeleteMapping("/{id}")
    public void deleteAvailability(@PathVariable int id) {
        availabilityService.deleteAvailability(id);
    }
}