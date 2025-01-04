package com.isi.microservices.student;


import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentResponse addStudent(StudentRequest request);
    StudentResponse updateStudent(StudentRequest request);
    StudentResponse getStudent(Long studentId);
    List<StudentResponse> getAllStudents();
    void deleteStudentById(Long studentId);
}
