package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;

@Entity
public class VideoProj extends MaterielPedagogique{

    private String resolution;
    private String type_de_lampe;
    private int frequence;

    public VideoProj(int id, String nom, String resolution, String type_de_lampe, String marque, String model, int frequence) {
        
        super(id, nom, marque, model);
        this.resolution = resolution;
        this.type_de_lampe = type_de_lampe;
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

    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }


    
    
}
