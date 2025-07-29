package com.example.ReservationSalleMateriel.Message;

import java.time.LocalDate;

// ReservSalleRequest.java
public class ReservSalleRequest {
    private LocalDate date;
    private String horaire;
    private Integer salleId;
    private Integer enseignantMatricule;
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getHoraire() {
        return horaire;
    }
    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }
    public Integer getSalleId() {
        return salleId;
    }
    public void setSalleId(Integer salleId) {
        this.salleId = salleId;
    }
    public Integer getEnseignantMatricule() {
        return enseignantMatricule;
    }
    public void setEnseignantMatricule(Integer enseignantMatricule) {
        this.enseignantMatricule = enseignantMatricule;
    }
    
    
}
