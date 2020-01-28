package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Unite;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.UniteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Unites")
public class UniteController {

    @Autowired
    private final UniteRepository uniteRepository;
    private Logger logger = Logger.getLogger(UniteController.class);

    public UniteController(UniteRepository uniteRepository) {
        this.uniteRepository = uniteRepository;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Unite> getList() {
        List<Unite> unites = (List<Unite>) uniteRepository.findAll();
        return unites;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Unite afficherUnite(@PathVariable int id) {
        Optional<Unite> unite = uniteRepository.findById(id);
        return unite.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Unite saveUnite(@RequestBody Unite unite) {
        unite = uniteRepository.save(unite);
        return unite;
    }


}
