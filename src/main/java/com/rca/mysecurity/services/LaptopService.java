package com.rca.mysecurity.services;


import com.rca.mysecurity.dto.LaptopDTO;
import com.rca.mysecurity.entity.Laptop;
import com.rca.mysecurity.entity.Student;
import com.rca.mysecurity.exceptions.ResourceNotFoundException;
import com.rca.mysecurity.repository.LaptopRepository;
import com.rca.mysecurity.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Optional<Laptop> getLaptopById(int lapId) {
        return laptopRepository.findById(lapId);
    }

    @Transactional
    public Laptop createLaptop(LaptopDTO laptopDTO) {
        // Validate and process DTO
        Optional<Student> studentOptional = studentRepository.findById(laptopDTO.getStudentId());
        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException("Student not found with id " + laptopDTO.getStudentId());
        }

        // Convert DTO to entity
        Student student = studentOptional.get();
        Laptop laptop = new Laptop(laptopDTO.getBrand(), laptopDTO.getSn(), student);

        // Save entity
        return laptopRepository.save(laptop);



    }

    public Laptop updateLaptop(int lapId, Laptop laptopDetails) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(lapId);

        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            laptop.setBrand(laptopDetails.getBrand());
            laptop.setSn(laptopDetails.getSn());
            laptop.setStudent(laptopDetails.getStudent());
            return laptopRepository.save(laptop);
        } else {
            throw new ResourceNotFoundException("Laptop not found with id " + lapId);
        }
    }

    public void deleteLaptop(int lapId) {
        laptopRepository.deleteById(lapId);
    }
}