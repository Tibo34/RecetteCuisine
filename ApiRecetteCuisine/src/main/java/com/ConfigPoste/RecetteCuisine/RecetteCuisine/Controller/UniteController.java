package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Unite;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.UniteRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Unites")
public class UniteController {

    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<Unite> getList(){
        List<Unite> unites=new UniteRepository().getAll();
        return unites;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public Unite afficherUnite(@PathVariable int id) {
        Unite unite=new UniteRepository().getById(1);
        return unite;
    }


}
