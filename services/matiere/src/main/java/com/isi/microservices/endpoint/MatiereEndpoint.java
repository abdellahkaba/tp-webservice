package com.isi.microservices.endpoint;



import com.isi.microservices.*;
import com.isi.microservices.dto.MatiereRequest;
import com.isi.microservices.service.MatiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class MatiereEndpoint {

    private static final String NAMESPACE_URI = "http://www.isi.com/matiere";

    private final MatiereService matiereService;

    /**
     * Récupérer une matière par son ID
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMatiereRequest")
    @ResponsePayload
    public GetMatiereResponse getMatiereById(@RequestPayload GetMatiereRequest request) {
        var response = new GetMatiereResponse();
        var matiere = matiereService.getMatiereById(request.getId());
        response.setMatiere(mapToSoapMatiere(matiere));
        return response;
    }

    /**
     * Enregistrer une nouvelle matière
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveMatiereRequest")
    @ResponsePayload
    public SaveMatiereResponse saveMatiere(@RequestPayload SaveMatiereRequest request) {
        var matiereRequest = new MatiereRequest(null, request.getName(), request.getCredits());
        var savedMatiere = matiereService.saveMatiere(matiereRequest);
        var response = new SaveMatiereResponse();
        response.setMatiere(mapToSoapMatiere(savedMatiere));
        return response;
    }

    /**
     * Récupérer toutes les matières
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllMatieresRequest")
    @ResponsePayload
    public GetAllMatieresResponse getAllMatieres() {
        var response = new GetAllMatieresResponse();
        var matieres = matiereService.getAllMatieres()
                .stream()
                .map(this::mapToSoapMatiere)
                .collect(Collectors.toList());
        response.getMatiere().addAll(matieres);
        return response;
    }

    /**
     * Mettre à jour une matière
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateMatiereRequest")
    @ResponsePayload
    public UpdateMatiereResponse updateMatiere(@RequestPayload UpdateMatiereRequest request) {
        var matiereRequest = new MatiereRequest(request.getId(), request.getName(), request.getCredits());
        var updatedMatiere = matiereService.updateMatiere(matiereRequest);
        var response = new UpdateMatiereResponse();
        response.setMatiere(mapToSoapMatiere(updatedMatiere));
        return response;
    }

    /**
     * Supprimer une matière
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMatiereRequest")
    @ResponsePayload
    public DeleteMatiereResponse deleteMatiere(@RequestPayload DeleteMatiereRequest request) {
        matiereService.deleteMatiere(request.getId());
        return new DeleteMatiereResponse(); // Vide pour indiquer le succès
    }

    /**
     * Mapper une MatiereResponse vers une Matiere SOAP
     */
    private Matiere mapToSoapMatiere(com.isi.microservices.dto.MatiereResponse matiereResponse) {
        var matiere = new Matiere();
        matiere.setId(matiereResponse.getId());
        matiere.setName(matiereResponse.getName());
        matiere.setCredits(matiereResponse.getCredits());
        return matiere;
    }
}
