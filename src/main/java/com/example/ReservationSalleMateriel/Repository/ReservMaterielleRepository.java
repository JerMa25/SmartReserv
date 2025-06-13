package com.example.ReservationSalleMateriel.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReservationSalleMateriel.model.MaterielPedagogique;
import com.example.ReservationSalleMateriel.model.ReservMat;

public interface ReservMaterielleRepository extends JpaRepository<ReservMat,Integer>{
    
    List<ReservMat> findByMatId(MaterielPedagogique matId);


}
