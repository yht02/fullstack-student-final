package com.fullstack.studentsystem.controller;


import com.fullstack.studentsystem.model.Student;
import com.fullstack.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin("http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public String createStudent(@RequestBody final Student student) {
        studentService.saveStudent(student);
        return "New student is added";
    }

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable final Integer id) {
        Optional<Student> foundStudent = studentService.findStudentById(id);

        return foundStudent.map(
                        student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody final Student student,
                                                 @PathVariable final Integer id) {

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean isStudentExist = studentService.isStudentExists(id);

        if (isStudentExist) {
            studentService.saveStudent(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentById(@PathVariable final Integer id) {
        if (studentService.isStudentExists(id)) {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
