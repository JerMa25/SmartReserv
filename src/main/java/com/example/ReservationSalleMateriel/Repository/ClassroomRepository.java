package com.example.ReservationSalleMateriel.Repository;

import com.example.ReservationSalleMateriel.model.SalleDeCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<SalleDeCours, Integer> {

    List<SalleDeCours> findAll();
    Optional<SalleDeCours> findById(Integer id);
    SalleDeCours save(SalleDeCours salleDeCours);
    void deleteById(Integer id);
}