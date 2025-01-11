package com.isi.microservices.services;

import com.isi.microservices.model.Classe;

import java.util.List;

public interface IClasseService {
    List<Classe> getAllClasses();
    Classe getClasse(int id);
    Classe addClasse(Classe classe);
    Classe updateClasse(int id , Classe classe);
    boolean deleteClasse(int id);
}
