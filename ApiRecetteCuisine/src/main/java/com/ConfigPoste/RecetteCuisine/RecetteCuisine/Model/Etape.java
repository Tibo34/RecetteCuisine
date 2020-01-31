package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
@Table(name = "Etape")
public class Etape {

    private int id;
    private String content;
    private int recette_id;

    @Basic
    @Column(name = "rang")
    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    private int rang;

    @Column(name = "recette_id")
    public int getRecette_id() {
        return recette_id;
    }

    public void setRecette_id(int recette_id) {
        this.recette_id = recette_id;
    }

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
    @Column(name = "content",nullable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
