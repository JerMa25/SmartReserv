package com.example.ReservationSalleMateriel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class SalleDeCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nbPlaces;
    private String description;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNbPlaces() {
        return nbPlaces;
    }
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    public String getDescription() {return description;}
    public void setDescription(String description){this.description = description;}

    public SalleDeCours(int id, int nbPlaces) {
        this.id = id;
        this.nbPlaces = nbPlaces;
    }

   public  SalleDeCours(){

    }
    

}
