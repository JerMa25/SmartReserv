package com.example.ReservationSalleMateriel.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table
public class ReservSalle {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT AUTO_INCREMENT")
    private Integer id;
    private LocalDate cree_Le;
    private LocalDate date;
    private String horaire;
    private String state;
    @ManyToOne
    private SalleDeCours salleId;
    @ManyToOne
    private Enseignant enseignantId;
    public ReservSalle( LocalDate cree_Le, LocalDate date, String horaire, String state, SalleDeCours salleId,
            Enseignant enseignantId) {
        
        this.cree_Le = cree_Le;
        this.date = date;
        this.horaire = horaire;
        this.state = state;
        this.salleId = salleId;
        this.enseignantId = enseignantId;
    }

   public  ReservSalle(){

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
