package com.example.demo.students;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    
	public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        /* Optional<Student> studentOptional = studentRepository.findStudentById(id);
        if(!studentOptional.isPresent()){
            throw new IllegalStateException("Student doesn't exist");
        } */

         boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Student doesn't exist");
        }

        studentRepository.deleteById(id); 
        
    }

    @Transactional
    public void updateStudent(long id, String name, String email){
        Student student = studentRepository.findById(id)
        .orElseThrow(()->new IllegalStateException("Student doesn't exist"));

        if(name!= null && name.length()>0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email!= null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}