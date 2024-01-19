package com.fullstack.studentsystem.service;

import com.fullstack.studentsystem.model.Student;
import com.fullstack.studentsystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(final StudentRepository studentRepository){

        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(final Student student) {

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudentById(Integer id) {
        try {
            studentRepository.deleteById(id);

        } catch (EmptyResultDataAccessException err){
            log.debug("Attempting to delete a non-existent record", err);
        }
    }

    @Override
    public boolean isStudentExists(Integer id) {
        return studentRepository.existsById(id);
    }
}
