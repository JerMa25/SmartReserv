package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Entity;

@Entity
public class Ordinateur extends MaterielPedagogique{

    private String processeur;
    private int stockage;
    private String memoire;
    
    public Ordinateur(){

    }
    public Ordinateur(int id, String nom,String processeur, int stockage, String memoire, String marque, String model) {

        super(id, nom, marque, model);
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
    public int getStockage() {
        return stockage;
    }
    public void setStockage(int stockage) {
        this.stockage = stockage;
    }
    public String getMemoire() {
        return memoire;
    }
    public void setMemoire(String memoire) {
        this.memoire = memoire;
    }

    
    
}
