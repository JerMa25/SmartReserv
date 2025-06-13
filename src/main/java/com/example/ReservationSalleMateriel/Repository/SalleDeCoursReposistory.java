package com.example.ReservationSalleMateriel.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.model.SalleDeCours;

public interface SalleDeCoursReposistory extends JpaRepository<SalleDeCours,Integer>{
    
    //find all the hall that have not been reserved
    @Query("select s from  SalleDeCours s where s.id not in (select p.salleId.id from ReservSalle p where p.date=:date)")
    List<SalleDeCours> findAvailableSallesForDay( @Param( "date") LocalDate date);
     List<SalleDeCours> findByNbPlacesGreaterThanEqual(int nbPlaces);

     //find all the halls that have already been reserved for a given date
    @Query("select s from  SalleDeCours s where s.id  in (select p.salleId.id from ReservSalle p where p.date= :date)")
    List<SalleDeCours> findReservedSalle( @Param( "date") LocalDate date);


   

    

}
 