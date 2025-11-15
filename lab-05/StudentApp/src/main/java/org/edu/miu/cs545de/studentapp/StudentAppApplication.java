package org.edu.miu.cs545de.studentapp;

import org.edu.miu.cs545de.studentapp.model.Address;
import org.edu.miu.cs545de.studentapp.model.Student;
import org.edu.miu.cs545de.studentapp.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(StudentRepository studentRepository) {
        return args -> {
            // Clear existing for a clean start (optional)
            studentRepository.deleteAll();

            studentRepository.save(new Student(
                    "Alice",
                    "111-111",
                    "alice@example.com",
                    new Address("Street 1", "New York", "10001")
            ));

            studentRepository.save(new Student(
                    "Bob",
                    "222-222",
                    "bob@example.com",
                    new Address("Street 2", "Dallas", "75001")
            ));

            studentRepository.save(new Student(
                    "Charlie",
                    "333-333",
                    "charlie@example.com",
                    new Address("Street 3", "New York", "10002")
            ));

            studentRepository.save(new Student(
                    "Alice",
                    "444-444",
                    "alice2@example.com",
                    new Address("Street 4", "Chicago", "60007")
            ));

            studentRepository.save(new Student(
                    "David",
                    "555-555",
                    "david@example.com",
                    new Address("Street 5", "Dallas", "75002")
            ));
        };
    }
}

