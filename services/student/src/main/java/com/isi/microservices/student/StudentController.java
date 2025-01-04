package com.isi.microservices.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(
            @Valid @RequestBody StudentRequest request
    ){
        return ResponseEntity.ok(service.addStudent(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(service.getAllStudents());
    }
    @GetMapping("/{student-id}")
    public ResponseEntity<StudentResponse> findStudentById(
            @PathVariable("student-id") Long studentId
    ){
        return ResponseEntity.ok(service.getStudent(studentId));
    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponse> updateStudent(
            @Valid @RequestBody StudentRequest request) {
        StudentResponse updatedStudent = service.updateStudent(request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/by-id/{student-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("student-id") Long studentId) {
        service.deleteStudentById(studentId);
        return ResponseEntity.noContent().build();
    }


}
