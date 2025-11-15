package org.edu.miu.cs545de.studentapp.repository;

import org.edu.miu.cs545de.studentapp.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    // Get all students with a certain name
    List<Student> findByName(String name);

    // Get a student with a certain phone number
    Optional<Student> findByPhoneNumber(String phoneNumber);

    // Get all students from a certain city
    List<Student> findByAddress_City(String city);
}

