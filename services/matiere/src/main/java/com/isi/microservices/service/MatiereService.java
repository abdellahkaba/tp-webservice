package com.isi.microservices.service;

import com.isi.microservices.dto.MatiereRequest;
import com.isi.microservices.dto.MatiereResponse;
import com.isi.microservices.mapper.MatiereMapper;
import com.isi.microservices.model.Matiere;
import com.isi.microservices.repository.MatiereRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatiereService {


    private final MatiereRepository repository;
    private final MatiereMapper mapper;

    public MatiereResponse saveMatiere(MatiereRequest request) {
        return mapper.fromMatiere(repository.save(mapper.toMatiere(request)));
    }

    public MatiereResponse getMatiereById(Long id) {
        return repository.findById(id)
                .map(mapper::fromMatiere)
                .orElseThrow(() -> new EntityNotFoundException("Matiere non trouvée"));
    }

    public List<MatiereResponse> getAllMatieres() {
        return repository.findAll()
                .stream()
                .map(mapper::fromMatiere)
                .collect(Collectors.toList());
    }

    public MatiereResponse updateMatiere(MatiereRequest request) {
        var matiere = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Matiere non trouvée"));
        matiere.setName(request.name());
        matiere.setCredits(request.credits());
        var updateMatiere = repository.save(matiere);
        return mapper.fromMatiere(updateMatiere);
    }

    public  void deleteMatiere(Long id) {
        Matiere matiere = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cette n'existe pas "));
        repository.delete(matiere);
    }

}

