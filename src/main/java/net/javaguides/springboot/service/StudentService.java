package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Student;
import java.util.List;

import org.springframework.data.repository.query.Param;

public interface StudentService {
    List<Student> findAllStudents();

    Student findStudentById(Long studentId);

    void saveStudent(Student student);

    void deleteStudentById(Long studentId);
    
   

    
}
