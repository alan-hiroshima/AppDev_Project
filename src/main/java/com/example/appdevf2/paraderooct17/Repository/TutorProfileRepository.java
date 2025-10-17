package com.example.appdevf2.paraderooct17.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;

@Repository
public interface TutorProfileRepository extends JpaRepository<TutorProfileEntity, Integer> {

}
