package com.isi.microservices.mapper;


import com.isi.microservices.dto.MatiereRequest;
import com.isi.microservices.dto.MatiereResponse;
import com.isi.microservices.model.Matiere;
import org.springframework.stereotype.Component;

@Component
public class MatiereMapper {

    public Matiere toMatiere(MatiereRequest request) {
        if (request == null) {
            return null;
        }

        return Matiere.builder()
                .id(request.id())
                .name(request.name())
                .credits(request.credits())
                .build();
    }

    public MatiereResponse fromMatiere(Matiere matiere) {
        return MatiereResponse.builder()
                .id(matiere.getId())
                .name(matiere.getName())
                .credits(matiere.getCredits())
                .build();
    }
}
