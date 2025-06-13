package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Enseignant")
public class Enseignant {

    @Id
    private int matricule;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private int age;

    @Column
    private String sex;

    @Column
    private String tel;
    @Column
    private String email;

    public Enseignant(){

    }
    
    public Enseignant(int matricule, String nom, String prenom, int age, String sex, String tel, String email) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.email = email;
    }

    public int getMatricule() {
        return matricule;
    }
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    


}
