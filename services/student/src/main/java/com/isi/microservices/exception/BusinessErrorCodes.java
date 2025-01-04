package com.isi.microservices.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    ENTITY_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Entité non trouvée"),
    DUPLICATE_EMAIL(409, HttpStatus.CONFLICT, "Email déjà utilisé"),
    STATUS_NOT_FOUND(404, HttpStatus.NOT_FOUND,"Le status non trouve"),
    TYPE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Le type non trouvé")
    ;
    private final int code;
    private final HttpStatus httpStatus;
    private final String description;


    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.httpStatus = status;
        this.description = description;

    }
}
