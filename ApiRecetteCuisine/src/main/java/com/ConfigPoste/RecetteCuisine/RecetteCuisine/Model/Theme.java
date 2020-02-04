package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
@Table(name = "Theme")
public class Theme {
    private int id;
    private String nom;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
