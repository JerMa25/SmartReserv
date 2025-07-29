package com.example.ReservationSalleMateriel.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ReservMat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate cree_Le;
    private LocalDate date;
    private String horaire;
    private String state;

    @OneToOne
    private MaterielPedagogique matId;

    @OneToOne
    private Enseignant enseignantId;
    public ReservMat( LocalDate cree_Le, LocalDate date, String horaire, String state, MaterielPedagogique matId,
            Enseignant enseignantId) {
        
        this.cree_Le = cree_Le;
        this.date = date;
        this.horaire = horaire;
        this.state = state;
        this.matId = matId;
        this.enseignantId = enseignantId;
    }

    public ReservMat(){

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
    public MaterielPedagogique getMatId() {
        return matId;
    }
    public void setMatId(MaterielPedagogique matId) {
        this.matId = matId;
    }
    public Enseignant getEnseignantId() {
        return enseignantId;
    }
    public void setEnseignantId(Enseignant enseignantId) {
        this.enseignantId = enseignantId;
    }    


    

    

}
