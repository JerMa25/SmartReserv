package com.example.ReservationSalleMateriel.Repository;

import com.example.ReservationSalleMateriel.model.ReservMat;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository Layer
@Repository
public interface ReservMatRepository extends JpaRepository<ReservMat, Integer> {

    List<ReservMat> findAll();
    Optional<ReservMat> findById(Integer id);
    ReservMat save(ReservMat reservMat);
    void deleteById(Integer id);
}
