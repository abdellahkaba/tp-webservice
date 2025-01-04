package com.isi.microservices.repository;

import com.isi.microservices.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
