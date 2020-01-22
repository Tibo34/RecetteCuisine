package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
public class IngredientRecette {
    private Integer recetteId;
    private Integer ingredientId;
    private Double quantite;
    private int id;
    private Recette recetteByRecetteId;
    private Ingredient ingredientByIngredientId;

    @Basic
    @Column(name = "recette_id", nullable = true,insertable=false,updatable = false)
    public Integer getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(Integer recetteId) {
        this.recetteId = recetteId;
    }

    @Basic
    @Column(name = "ingredient_id", nullable = true,insertable=false,updatable = false)
    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Basic
    @Column(name = "quantite", nullable = true, precision = 0)
    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientRecette that = (IngredientRecette) o;

        if (id != that.id) return false;
        if (recetteId != null ? !recetteId.equals(that.recetteId) : that.recetteId != null) return false;
        if (ingredientId != null ? !ingredientId.equals(that.ingredientId) : that.ingredientId != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recetteId != null ? recetteId.hashCode() : 0;
        result = 31 * result + (ingredientId != null ? ingredientId.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "recette_id", referencedColumnName = "id")
    public Recette getRecetteByRecetteId() {
        return recetteByRecetteId;
    }

    public void setRecetteByRecetteId(Recette recetteByRecetteId) {
        this.recetteByRecetteId = recetteByRecetteId;
    }

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    public Ingredient getIngredientByIngredientId() {
        return ingredientByIngredientId;
    }

    public void setIngredientByIngredientId(Ingredient ingredientByIngredientId) {
        this.ingredientByIngredientId = ingredientByIngredientId;
    }
}
