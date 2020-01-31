package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;


import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Etape;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.EtapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Etapes")
public class EtapeController {

    @Autowired
    private final EtapeRepository etapeRepository;

    public EtapeController(EtapeRepository etapeRepository) {
        this.etapeRepository = etapeRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Etape> getList() {
        List<Etape> etapes = (List<Etape>) etapeRepository.findAll();
        return etapes;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Etape afficherEtape(@PathVariable int id) {
        Optional<Etape> etape = etapeRepository.findById(id);
        return etape.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Etape createIngredient(@RequestBody Etape etape) {
        return etapeRepository.save(etape);
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Etape updateIngredient(@RequestBody Etape newIngredient) {
        //TODO: implement update
        return etapeRepository.save(newIngredient);
    }
}
