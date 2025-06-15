package com.example.ReservationSalleMateriel.Repository;

import com.example.ReservationSalleMateriel.model.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdinateurPortableRepository extends JpaRepository<Ordinateur, Integer> {

    List<Ordinateur> findAll();
    Optional<Ordinateur> findById(Integer id);
    Ordinateur save(Ordinateur ordinateur);
    void deleteById(Integer id);
}
