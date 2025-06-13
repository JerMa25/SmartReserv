package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salle_de_cours")
public class SalleDeCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int nbPlaces;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getNbPlaces() {
        return nbPlaces;
    }
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    public SalleDeCours( int nbPlaces) {
     
        this.nbPlaces = nbPlaces;
    }

   public  SalleDeCours(){

    }
    

}
