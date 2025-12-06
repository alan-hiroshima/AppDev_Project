package com.example.appdevf2.paraderooct17.Repository;

import com.example.appdevf2.paraderooct17.Entity.DisputeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository extends JpaRepository<DisputeEntity, Integer> {
    // You can add custom finders here later if needed, e.g.:
    // List<DisputeEntity> findByStatus(String status);
}