package com.fullstack.studentsystem.service;

import com.fullstack.studentsystem.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();

    Optional<Student> findStudentById(Integer id);

    void deleteStudentById(Integer id);

    boolean isStudentExists(Integer id);
}
