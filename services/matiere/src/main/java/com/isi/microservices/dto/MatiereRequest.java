package com.isi.microservices.dto;

public record MatiereRequest(
        Long id,
        String name,
        int credits
) {
}
