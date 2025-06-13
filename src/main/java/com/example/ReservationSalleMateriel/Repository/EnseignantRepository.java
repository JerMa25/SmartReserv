package com.example.ReservationSalleMateriel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReservationSalleMateriel.model.Enseignant;
import java.util.List;


@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer>{

        
        Optional<Enseignant> findByEmail(String email); 
        Optional<Enseignant>  findByMatricule(Integer matricule);
        
}
