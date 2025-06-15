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
    private String marque;
    private String model;


    public MaterielPedagogique(){
        
    }
    public MaterielPedagogique(int id, String nom, String marque, String model) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.model = model;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMarque() {return marque;}
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    
   


    

}
