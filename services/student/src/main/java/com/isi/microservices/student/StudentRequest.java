package com.isi.microservices.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record StudentRequest(
        Long id,
        @NotBlank(message = "")
        @NotEmpty(message = "")
        String firstName,
        @NotBlank(message = "")
        @NotEmpty(message = "")
        String lastName,
        @NotBlank(message = "")
        @Email(message = "")
        String email
) {
}
