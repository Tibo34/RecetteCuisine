package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Recette")
public class Recette {


    private int id;
    private String nom;
    private Collection<Ingredient> ingredients;
    private int personneMin=4;
    private int tempCuisson=0;
    private int tempPreparation=0;
    private String image;
    private String shortContent;
    private String content;
    private Theme theme;





    @Basic
    @Column(name="personne")
    public int getPersonneMin() {
        return personneMin;
    }

    public void setPersonneMin(int personneMin) {
        this.personneMin = personneMin;
    }



    @Basic
    @Column(name = "tempCuisson")
    public int getTempCuisson() {
        return tempCuisson;
    }

    public void setTempCuisson(int tempCuisson) {
        this.tempCuisson = tempCuisson;
    }

    @Basic
    @Column(name = "tempPreparation")
    public int getTempPreparation() {
        return tempPreparation;
    }

    public void setTempPreparation(int tempPreparation) {
        this.tempPreparation = tempPreparation;
    }


    @OneToMany
    @JoinColumn(name="recette_id")
    public Collection<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(Collection<Etape> etapes) {
        this.etapes = etapes;
    }

    private Collection<Etape> etapes;





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

    @Basic
    @Column(name="description" ,nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Basic
    @Column(name="descriptionShort" ,nullable = false)
    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    @OneToMany
    @JoinColumn(name = "recette_id", insertable = false, updatable = false)
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredientsById) {
        this.ingredients = ingredientsById;
    }

    @Basic
    @Column(name="image",nullable = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void update(Recette recette){
        content=recette.getContent();
        shortContent=recette.getShortContent();
        ingredients=recette.getIngredients();
        etapes=recette.etapes;
    }
}
