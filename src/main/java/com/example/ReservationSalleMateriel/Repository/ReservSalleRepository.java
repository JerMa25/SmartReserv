package com.example.ReservationSalleMateriel.Repository;

import com.example.ReservationSalleMateriel.model.ReservSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservSalleRepository extends JpaRepository<ReservSalle, Integer> {

    List<ReservSalle> findAll();
    Optional<ReservSalle> findById(Integer id);
    ReservSalle save(ReservSalle reservSalle);
    void deleteById(Integer id);
}