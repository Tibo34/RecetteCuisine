package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Etape;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Theme;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.EtapeRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.ThemeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Themes")
public class ThemeController {
    @Autowired
    private final ThemeRepository themeRepository;
    private Logger logger = Logger.getLogger(EtapeController.class);

    public ThemeController(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Theme> getList() {
        List<Theme> themes = (List<Theme>) themeRepository.findAll();
        return themes;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Theme afficherEtape(@PathVariable int id) {
        Optional<Theme> theme = themeRepository.findById(id);
        return theme.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Theme createIngredient(@RequestBody Theme theme) {
        logger.debug(theme);
        return themeRepository.save(theme);
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Theme updateIngredient(@RequestBody Theme newTheme) {
        //TODO: implement update
        return themeRepository.save(newTheme);
    }

}
