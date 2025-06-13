package com.example.ReservationSalleMateriel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.model.SalleDeCours;

public interface ReservSalleRepository extends JpaRepository<ReservSalle,Integer> {

    List<ReservSalle> findBySalleId(SalleDeCours salleId);
}
