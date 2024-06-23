package com.rca.mysecurity.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.rca.mysecurity.entity.Student;
import com.rca.mysecurity.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    public void addStudent(Student st) {
        repo.save(st);
    }

    public List<Student> getAllStudentsSortedByName() {
        // Fetch all students sorted by name
        return repo.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }
}

