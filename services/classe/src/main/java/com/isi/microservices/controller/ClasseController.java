package com.isi.microservices.controller;

import com.isi.microservices.model.Classe;
import com.isi.microservices.services.IClasseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClasseController {
    private final IClasseService classeService;

    public ClasseController(IClasseService classeService) {
        this.classeService = classeService;
    }

    @QueryMapping
    public List<Classe> listClasses() {
        return classeService.getAllClasses();
    };

    @QueryMapping
    public Classe getClasseById(@Argument int id) {
        return classeService.getClasse(id);
    }

    @MutationMapping
    public Classe createClasse(@Argument String name) {
        Classe classe = new Classe();
        classe.setName(name);
        return classeService.addClasse(classe);
    }
    @MutationMapping
    public Classe updateClasse(@Argument int id, @Argument String name) {
        Classe classe = new Classe();
        classe.setName(name);
        return classeService.updateClasse(id, classe);
    }

    @MutationMapping
    public Classe deleteClasse(@Argument int id) {
        Classe classe = new Classe();
        classe.setId(id);
        if (classeService.deleteClasse(id)) {
            return classe;
        }
        return null;
    }

}
