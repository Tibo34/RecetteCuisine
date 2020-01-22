package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Unite", schema = "recetteCuisine", catalog = "")
public class UniteEntity {
    private int id;
    private String value;
    private Collection<TypeIngredientEntity> typeIngredientsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniteEntity that = (UniteEntity) o;

        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uniteByUniteId")
    public Collection<TypeIngredientEntity> getTypeIngredientsById() {
        return typeIngredientsById;
    }

    public void setTypeIngredientsById(Collection<TypeIngredientEntity> typeIngredientsById) {
        this.typeIngredientsById = typeIngredientsById;
    }
}
