package com.example.ReservationSalleMateriel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReservationSalleMateriel.model.Enseignant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,String>{

    List<Enseignant> findAll();
    List<Enseignant> findByFormation(String formation);
    Optional<Enseignant> findById(String matricule);
    Enseignant save(Enseignant enseignant);
    void deleteById(String matricule);
}
