package com.isi.microservices.student;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toStudent(StudentRequest request) {
        if (request == null) {
            return null;
        }
        return Student.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
    }

    public StudentResponse fromStudent(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
    }
}
