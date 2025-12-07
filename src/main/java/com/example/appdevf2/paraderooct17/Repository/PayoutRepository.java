package com.example.appdevf2.paraderooct17.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appdevf2.paraderooct17.Entity.PayoutEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;

@Repository
public interface PayoutRepository extends JpaRepository<PayoutEntity, Integer> {

    List<PayoutEntity> findByTutorProfile(TutorProfileEntity tutorProfile);
}
