package com.isi.microservices.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException exp) {
      return ResponseEntity
              .status(BusinessErrorCodes.ENTITY_NOT_FOUND.getHttpStatus())
              .body(
                      ExceptionResponse.builder()
                                      .businessErrorCode(BusinessErrorCodes.ENTITY_NOT_FOUND.getCode())
                                      .businessErrorDescription(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription())
                                      .error(exp.getMessage())
                                      .build()
              );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException exp) {
      Set<String> errors = new HashSet<>();
      exp.getBindingResult().getAllErrors().forEach(error -> {
          //var fieldName = ((FieldError) error).getField();
          var errorMessage = error.getDefaultMessage();
          errors.add(errorMessage);
      });
      return ResponseEntity
              .status(BAD_REQUEST)
              .body(ExceptionResponse.builder().validationErrors(errors).build());
  }

    @ExceptionHandler(EmailConflictException.class)
    public ResponseEntity<ExceptionResponse> handleException(EmailConflictException exp) {
        return ResponseEntity
                .status(BusinessErrorCodes.DUPLICATE_EMAIL.getHttpStatus())
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessErrorCodes.DUPLICATE_EMAIL.getCode())
                                .businessErrorDescription(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }
}
