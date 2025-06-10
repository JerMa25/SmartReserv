package com.example.ReservationSalleMateriel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReservationSalleMateriel.model.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant,Integer>{
    
}
