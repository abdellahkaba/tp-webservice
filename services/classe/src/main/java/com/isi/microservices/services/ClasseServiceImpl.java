package com.isi.microservices.services;

import com.isi.microservices.model.Classe;
import com.isi.microservices.repository.ClasseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ClasseServiceImpl implements IClasseService {

    private final ClasseRepository repository;

    public List<Classe> getAllClasses(){
        return repository.findAll();
    }
    public Classe getClasse(int id){
        return repository.findById(id).get();
    }
    public Classe addClasse(Classe classe){
        return repository.save(classe);
    }
    public Classe updateClasse(int id , Classe classe){
        return repository.findById(id).map(c -> {
            c.setName(classe.getName());
            return repository.save(c);
        }).orElse(null);
    }
    public boolean deleteClasse(int id){
        try{
            repository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
