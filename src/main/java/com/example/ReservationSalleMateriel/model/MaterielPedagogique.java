package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 abstract public class MaterielPedagogique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;

    public MaterielPedagogique(){
        
    }
    public MaterielPedagogique(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getnom() {
        return nom;
    }
    public void setnom(String nom) {
        this.nom = nom;
    }

    
   


    

}
