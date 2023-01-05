package com.example.demo.students;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student hector = new Student(
                "Hector", 
                LocalDate.of(1999, Month.AUGUST, 5), 
                "Hectordapzs@gmail.com"
            );
            Student jose = new Student(
                "jose", 
                LocalDate.of(1998, Month.AUGUST, 5), 
                "jose@gmail.com"
            );

            repository.saveAll(List.of(hector, jose));
        };
    }
}
