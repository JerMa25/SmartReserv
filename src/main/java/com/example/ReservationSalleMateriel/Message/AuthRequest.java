package com.example.ReservationSalleMateriel.Message;

public class AuthRequest {
    private String email;
    private int matricule;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
}