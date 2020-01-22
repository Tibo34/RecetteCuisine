package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Recette {
    private int id;
    private String nom;
    private Collection<IngredientRecette> ingredientRecettesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 50)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recette recette = (Recette) o;

        if (id != recette.id) return false;
        if (nom != null ? !nom.equals(recette.nom) : recette.nom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "recetteByRecetteId")
    public Collection<IngredientRecette> getIngredientRecettesById() {
        return ingredientRecettesById;
    }

    public void setIngredientRecettesById(Collection<IngredientRecette> ingredientRecettesById) {
        this.ingredientRecettesById = ingredientRecettesById;
    }
}
