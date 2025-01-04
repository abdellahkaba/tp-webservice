package com.isi.microservices.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatiereResponse {

    private Long id;
    private String name;
    private int credits;


}
