package com.example.ReservationSalleMateriel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Enseignant")
public class Enseignant {

    @Id
    private String matricule;

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
    private String mail;

    @Column
    private String password;

    @Column
    private String formation;

    public Enseignant(){

    }

    public Enseignant(String matricule, String password){
        this.matricule = matricule;
        this.password = password;
    }
    
    public Enseignant(String matricule, String nom, String prenom, int age, String sex, String tel, String mail, String password, String formation) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.mail = mail;
        this.password = password;
        this.formation = formation;
    }

    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
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
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getFormation() {return formation;}
    public void setFormation(String formation) {this.formation = formation;}
}
