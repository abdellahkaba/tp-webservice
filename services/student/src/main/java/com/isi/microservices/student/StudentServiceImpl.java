package com.isi.microservices.student;


import com.isi.microservices.exception.BusinessErrorCodes;
import com.isi.microservices.exception.EmailConflictException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        if (repository.findByEmail(request.email()).isPresent()){
            throw new EmailConflictException(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription());
        }
        return mapper.fromStudent(repository.save(mapper.toStudent(request)));
    }

    @Override
    public StudentResponse updateStudent(StudentRequest request) {
        repository.findByEmail(request.email())
                .ifPresent(existingStudent -> {
                    if (!existingStudent.getId().equals(request.id())) {
                        throw new EmailConflictException(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription());
                    }
                });
        var student = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription()));

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        var updatedStudent = repository.save(student);
        return mapper.fromStudent(updatedStudent);
    }



    @Override
    public StudentResponse getStudent(Long id) {
        return repository.findById(id)
                .map(mapper::fromStudent)
                .orElseThrow(() -> new EntityNotFoundException(" L'Étudiant non trouvé !"));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(mapper::fromStudent)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Cet Etudiant n'existe pas avec ID : " + studentId));
        repository.delete(student);
    }
}
