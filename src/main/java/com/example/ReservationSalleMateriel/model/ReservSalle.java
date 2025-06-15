package com.example.ReservationSalleMateriel.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity

public class ReservSalle {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate cree_Le;
    private LocalDate date;
    private String horaire;
    private String state;

    @OneToOne
    @JoinColumn(name = "salleId", referencedColumnName = "id")
    private SalleDeCours salleId;

    @OneToOne
    @JoinColumn(name = "enseignantId", referencedColumnName = "matricule")
    private Enseignant enseignantId;

    public ReservSalle(int id, LocalDate cree_Le, LocalDate date, String horaire, String state, SalleDeCours salleId, Enseignant enseignantId) {
        this.id = id;
        this.cree_Le = cree_Le;
        this.date = date;
        this.horaire = horaire;
        this.state = state;
        this.salleId = salleId;
        this.enseignantId = enseignantId;
    }

   public  ReservSalle(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getCree_Le() {
        return cree_Le;
    }
    public void setCree_Le(LocalDate cree_Le) {
        this.cree_Le = cree_Le;
    }
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
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public SalleDeCours getSalleId() {
        return salleId;
    }
    public void setSalleId(SalleDeCours salleId) {
        this.salleId = salleId;
    }
    public Enseignant getEnseignantId() {
        return enseignantId;
    }
    public void setEnseignantId(Enseignant enseignantId) {
        this.enseignantId = enseignantId;
    }

    

}
