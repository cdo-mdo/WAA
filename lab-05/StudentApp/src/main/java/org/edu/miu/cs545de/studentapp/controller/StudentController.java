package org.edu.miu.cs545de.studentapp.controller;

import org.edu.miu.cs545de.studentapp.model.Student;
import org.edu.miu.cs545de.studentapp.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Get all students with a certain name
    //    /students/by-name?name=Alice
    @GetMapping("/by-name")
    public List<Student> getStudentsByName(@RequestParam String name) {
        return studentRepository.findByName(name);
    }

    // 3. Get a student with a certain phoneNumber
    //    /students/by-phone/111-111
    @GetMapping("/by-phone/{phone}")
    public ResponseEntity<Student> getStudentByPhone(@PathVariable String phone) {
        return studentRepository.findByPhoneNumber(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Get all students from a certain city
    //    /students/by-city?city=Dallas
    @GetMapping("/by-city")
    public List<Student> getStudentsByCity(@RequestParam String city) {
        return studentRepository.findByAddress_City(city);
    }
}

