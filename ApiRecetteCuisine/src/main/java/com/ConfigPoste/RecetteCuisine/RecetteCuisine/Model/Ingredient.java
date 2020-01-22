package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Ingredient {
    private int id;
    private String nom;
    private Integer typeId;
    private TypeIngredient typeIngredientByTypeId;
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

    @Basic
    @Column(name = "type_id", nullable = true,insertable=false,updatable = false)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public TypeIngredient getTypeIngredientByTypeId() {
        return typeIngredientByTypeId;
    }

    public void setTypeIngredientByTypeId(TypeIngredient typeIngredientByTypeId) {
        this.typeIngredientByTypeId = typeIngredientByTypeId;
    }

    @OneToMany(mappedBy = "ingredientByIngredientId")
    public Collection<IngredientRecette> getIngredientRecettesById() {
        return ingredientRecettesById;
    }

    public void setIngredientRecettesById(Collection<IngredientRecette> ingredientRecettesById) {
        this.ingredientRecettesById = ingredientRecettesById;
    }
}
