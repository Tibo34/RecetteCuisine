package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
@Table(name = "Ingredient")
public class Ingredient {
    private int id;
    private Integer typeId;
    private Double quantite;
    private Integer recetteId;
    private TypeIngredient type;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_id", nullable = true, updatable = false, insertable = false)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "quantite", nullable = true, precision = 0)
    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "recette_id", updatable = false)
    public Integer getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(Integer recetteId) {
        this.recetteId = recetteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (id != that.id) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;
        if (recetteId != null ? !recetteId.equals(that.recetteId) : that.recetteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        result = 31 * result + (recetteId != null ? recetteId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public TypeIngredient getType() {
        return type;
    }

    public void setType(TypeIngredient typeIngredientByTypeId) {
        this.type = typeIngredientByTypeId;
    }


}
