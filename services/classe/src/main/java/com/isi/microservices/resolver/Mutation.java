package com.isi.microservices.resolver;


import com.isi.microservices.model.Classe;
import com.isi.microservices.repository.ClasseRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final ClasseRepository repository;

    public Classe createClasse(String name) {
        Classe classe = new Classe();
        classe.setName(name);
        return repository.save(classe);
    }
}
