package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Unite;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.UniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Unites")
public class UniteController {

    private final UniteRepository uniteRepository;

    @Autowired
    UniteController() {
        this.uniteRepository = new UniteRepository();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="", method= RequestMethod.GET,produces = "application/json")
    public List<Unite> getList(){
        List<Unite> unites= uniteRepository.getAll();
        return unites;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public Unite afficherUnite(@PathVariable int id) {
        Unite unite= uniteRepository.getById(1);
        return unite;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
    public Unite saveUnite(@RequestBody Unite unite){
        unite=uniteRepository.save(unite);
        return unite;
    }


}
