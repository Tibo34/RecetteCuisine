package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TypeIngredient", schema = "recetteCuisine", catalog = "")
public class TypeIngredientEntity {
    private int id;
    private String nom;
    private Collection<IngredientEntity> ingredientsById;
    private UniteEntity uniteByUniteId;

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

        TypeIngredientEntity that = (TypeIngredientEntity) o;

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

    @OneToMany(mappedBy = "typeIngredientByTypeId")
    public Collection<IngredientEntity> getIngredientsById() {
        return ingredientsById;
    }

    public void setIngredientsById(Collection<IngredientEntity> ingredientsById) {
        this.ingredientsById = ingredientsById;
    }

    @ManyToOne
    @JoinColumn(name = "unite_id", referencedColumnName = "id")
    public UniteEntity getUniteByUniteId() {
        return uniteByUniteId;
    }

    public void setUniteByUniteId(UniteEntity uniteByUniteId) {
        this.uniteByUniteId = uniteByUniteId;
    }
}
