package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;

@Entity
@Table(name = "TypeIngredient")
public class TypeIngredient {
    private int id;
    private String nom;
    private Integer uniteId;
    private String image;

    private Unite unite;

    @Basic
    @Column(name = "image" ,nullable = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



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
    @Column(name = "nom", nullable = true, length = 50)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "unite_id", nullable = true, updatable = false, insertable = false)
    public Integer getUniteId() {
        return uniteId;
    }

    public void setUniteId(Integer uniteId) {
        this.uniteId = uniteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeIngredient that = (TypeIngredient) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (uniteId != null ? !uniteId.equals(that.uniteId) : that.uniteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (uniteId != null ? uniteId.hashCode() : 0);
        return result;
    }


    @ManyToOne
    @JoinColumn(name = "unite_id", referencedColumnName = "id")
    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite uniteByUniteId) {
        this.unite = uniteByUniteId;
    }
}
