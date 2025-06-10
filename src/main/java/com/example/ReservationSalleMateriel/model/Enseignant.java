package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Enseignant")
public class Enseignant {
    private int matricule;
    private String nom;

}
