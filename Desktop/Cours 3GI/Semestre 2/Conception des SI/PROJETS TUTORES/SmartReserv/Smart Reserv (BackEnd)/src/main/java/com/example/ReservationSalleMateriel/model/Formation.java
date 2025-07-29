package com.example.ReservationSalleMateriel.model;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

     @Column
    private String nom;
     @Column
    private int nbParticipants;
     @Column
    private LocalDate date;
     @Column
    private String horaire;
     

    @OneToOne
    private Enseignant responsableId;
    public Formation( String nom, int nbParticipants, LocalDate date, String horaire,Enseignant responsableId) {
       
        this.nom = nom;
        this.nbParticipants = nbParticipants;
        this.date = date;
        this.horaire = horaire;
        this.responsableId = responsableId;
    }

   public  Formation(){

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNbParticipants() {
        return nbParticipants;
    }
    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
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
    public Enseignant getResponsableId() {
        return responsableId;
    }
    public void setResponsableId(Enseignant responsableId) {
        this.responsableId = responsableId;
    }


    
    
}
