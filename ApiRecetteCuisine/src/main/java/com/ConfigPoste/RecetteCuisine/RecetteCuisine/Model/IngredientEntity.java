package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
@Table(name = "Ingredient", schema = "recetteCuisine", catalog = "")
public class IngredientEntity {
    private int id;
    private String nom;
    private TypeIngredientEntity typeIngredientByTypeId;

    @Id
    @Column(name = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientEntity that = (IngredientEntity) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public TypeIngredientEntity getTypeIngredientByTypeId() {
        return typeIngredientByTypeId;
    }

    public void setTypeIngredientByTypeId(TypeIngredientEntity typeIngredientByTypeId) {
        this.typeIngredientByTypeId = typeIngredientByTypeId;
    }
}
