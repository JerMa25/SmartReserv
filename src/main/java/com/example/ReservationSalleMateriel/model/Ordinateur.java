package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;

@Entity
public class Ordinateur extends MaterielPedagogique{

    private String processeur;
    private int stockage;
    private String memoire;
    
    public Ordinateur(){

    }
    public Ordinateur(String id, String nom,String processeur, int stockage, String memoire) {
       super(nom);
        this.processeur = processeur;
        this.stockage = stockage;
        this.memoire = memoire;
        
    }
    public String getProcesseur() {
        return processeur;
    }
    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }
    public int getstockage() {
        return stockage;
    }
    public void setstockage(int stockage) {
        this.stockage = stockage;
    }
    public String getmemoire() {
        return memoire;
    }
    public void setmemoire(String memoire) {
        this.memoire = memoire;
    }

    
    
}
