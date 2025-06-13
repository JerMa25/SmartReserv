package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VideoProj extends MaterielPedagogique{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String resolution;
    private String type_de_lampe;
    private String marque_et_modele;
    private int frequence;
    public VideoProj(int id, String nom, String resolution, String type_de_lampe, String marque_et_modele, int frequence) {
        
        super(nom);
        this.resolution = resolution;
        this.type_de_lampe = type_de_lampe;
        this.marque_et_modele = marque_et_modele;
        this.frequence = frequence;
    }
    public VideoProj(){

    }
    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getType_de_lampe() {
        return type_de_lampe;
    }

    public void setType_de_lampe(String type_de_lampe) {
        this.type_de_lampe = type_de_lampe;
    }


    public String getMarque_et_modele() {
        return marque_et_modele;
    }
    public void setMarque_et_modele(String marque_et_modele) {
        this.marque_et_modele = marque_et_modele;
    }
    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }


    
    
}
