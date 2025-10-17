package com.example.appdevf2.paraderooct17.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.SubjectEntity;
import com.example.appdevf2.paraderooct17.Repository.SubjectRepository;

@Service
public class SubjectService {
     private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectEntity saveSubject(SubjectEntity subject) {
        return subjectRepository.save(subject);
    }

    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
