package com.example.appdevf2.paraderooct17.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appdevf2.paraderooct17.Entity.SubjectEntity;
import com.example.appdevf2.paraderooct17.Service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public SubjectEntity createSubject(@RequestBody SubjectEntity subject) {
        return subjectService.saveSubject(subject);
    }

    @GetMapping
    public List<SubjectEntity> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}
