package com.example.ReservationSalleMateriel.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ReservationSalleMateriel.model.SalleDeCours;
import com.example.ReservationSalleMateriel.model.VideoProj;

public interface VideoProjectorRepository extends JpaRepository<VideoProj,Integer> {
    Optional<VideoProj> findByMarque_et_modele(String model);


    //find all the video projectors that have not been reserved
    @Query("select v from  VideoProj v where v.id not in (select p.matId.id from ReservMat p where p.date=:date)")
    List<VideoProj> findAvailableVideoProjectorsForDay( @Param( "date") LocalDate date);
   

     //find all the halls that have already been reserved for a given date
    @Query("select v from  VideoProj v where v.id  in (select p.matId.id from ReservMat p where p.date= :date)")
    List<VideoProj> findReservedVideoProjsForDay( @Param( "date") LocalDate date);

}
