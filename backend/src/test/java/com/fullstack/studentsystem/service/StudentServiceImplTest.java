package com.fullstack.studentsystem.service;

import com.fullstack.studentsystem.model.Student;
import com.fullstack.studentsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    private static final Student student = Student.builder()
            .id(1)
            .name("Alex")
            .major("Psychology")
            .address("Kuala Lumpur")
            .build();

    @Test
    public void testThatStudentIsCreated(){

        Mockito.when(studentRepository.save(student)).thenReturn(student);
        final Student result = studentServiceImpl.saveStudent(student);
        assertEquals(student, result);
    }

    @Test
    public void testThatGetAllStudentReturnsEmptyWhenNoStudent(){
        final List<Student> emptyStudentList = new ArrayList<>();
        Mockito.when(studentRepository.findAll()).thenReturn(emptyStudentList);

        final List<Student> result = studentServiceImpl.getAllStudents();
        assertEquals(emptyStudentList.size(), result.size());

    }


    @Test
    public void testThatGetAllStudentsReturnsStudentWhenNotEmpty(){
        final List<Student> studentList = studentRepository.findAll();
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);

        final List<Student> result = studentServiceImpl.getAllStudents();

        assertEquals(studentList.size(), result.size());
    }

    @Test
    public void testThatFindStudentByIdReturnsStudentWhenStudentExist(){

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        final Optional<Student> result = studentServiceImpl.findStudentById(student.getId());
        assertEquals(Optional.of(student), result);
    }

    @Test
    public void testThatFindStudentByIdReturnsEmptyWhenNoMatchedStudent(){
        int idThatDoesNotExist = -999;
        Mockito.when(studentRepository.findById(idThatDoesNotExist)).thenReturn(Optional.empty());
        final Optional<Student> result = studentServiceImpl.findStudentById(idThatDoesNotExist);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatDeleteStudentByIdIsWorking(){
        studentServiceImpl.deleteStudentById(student.getId());
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(student.getId());
    }

    @Test
    public void testIsStudentExistsReturnsFalseWhenStudentDoesNotExist() {
        Mockito.when(studentRepository.existsById(Mockito.any())).thenReturn(false);
        final boolean result = studentServiceImpl.isStudentExists(Mockito.any());
        assertFalse(result);
    }

}
