package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.PDF.PDFGenerator;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pdf")
public class PDFController {

    private Logger logger = Logger.getLogger(EtapeController.class);

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String createPDF(@RequestBody String message) {
        logger.debug("message pdf : "+message);
        PDFGenerator pdf=new PDFGenerator();
        pdf.createPDF(message);
        return "{rep:'PDF create'}";
    }
}
