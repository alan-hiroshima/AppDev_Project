package com.example.appdevf2.paraderooct17.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appdevf2.paraderooct17.Entity.AvailabilityEntity;

@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Integer> {
    // You can add queries here later, like:
    // List<AvailabilityEntity> findByDayOfWeek(String dayOfWeek);
}