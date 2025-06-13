package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
 abstract public class MaterielPedagogique {
   
    private Integer id;
    private String nom;

    public MaterielPedagogique(){
        
    }
    public MaterielPedagogique( String nom) {
       
        this.nom = nom;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getnom() {
        return nom;
    }
    public void setnom(String nom) {
        this.nom = nom;
    }

    
   


    

}
