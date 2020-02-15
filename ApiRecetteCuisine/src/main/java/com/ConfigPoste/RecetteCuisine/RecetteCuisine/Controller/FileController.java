package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;


import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Etape;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.FileImage;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Ingredient;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.Recette;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.RecetteRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/file")
public class FileController {


    private Logger logger = Logger.getLogger(FileController.class);
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private final RecetteRepository recetteRepository;
    @Autowired
    private final DBFileStorageService dbFileStorageService;

    public FileController(RecetteRepository recetteRepository, DBFileStorageService dbFileStorageService) {
        this.recetteRepository = recetteRepository;
        this.dbFileStorageService = dbFileStorageService;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        FileImage dbFile = null;
        try {
            dbFile = dbFileStorageService.storeFile(file);
        } catch (FileStorageException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(dbFile.getId()))
                .toUriString();
        return new UploadFileResponse(dbFile.getId(),dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        FileImage dbFile = null;
        try {
            dbFile = dbFileStorageService.getFile(fileId);
        } catch (MyFileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/pdf", method = RequestMethod.POST, produces = "application/json")
    public  ResponseEntity<Resource> dowloadPDF(@RequestBody String[] recettesId) throws IOException, DocumentException, MyFileNotFoundException {
        Document document = new Document();
        String fileName="iTextHelloWorld.pdf";
        logger.debug(recettesId);
        ByteArrayOutputStream outbyte=new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outbyte);
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Recettes de Cuisine", font);
        document.add(chunk);

        for (String idrecette:recettesId) {
            Recette recette=recetteRepository.findById(Integer.parseInt(idrecette)).get();
            Chunk title=new Chunk(recette.getNom(),font);
            FileImage fileImage=dbFileStorageService.getFile(String.valueOf(recette.getImageId()));
            Image img= Image.getInstance(fileImage.getData());
            document.add(title);
            document.add(img);
            for (Ingredient ingredient:recette.getIngredients()) {
                    Chunk nom=new Chunk(ingredient.getType().getNom(),font);
                    FileImage imgIngredient= dbFileStorageService.getFile(String.valueOf(ingredient.getType().getImageId()));
                    Image imgI=Image.getInstance(imgIngredient.getData());
                    document.add(nom);
                    document.add(imgI);
            }
            for (Etape etape:recette.getEtapes()) {
                Chunk content=new Chunk(etape.getContent(),font);
                document.add(content);
            }
        }
        document.close();
        FilePDFResponse file=new FilePDFResponse(fileName,outbyte.toByteArray(), MediaType.APPLICATION_PDF_VALUE);
       // return file;
       return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(outbyte.toByteArray()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/pdf/test")
    public FilePDFResponse dowloadPDFTest() throws IOException, DocumentException, MyFileNotFoundException {
        Document document = new Document();
        String fileName="iTextHelloWorld.pdf";
        File pdf=new File(fileName);
        FileOutputStream out=new FileOutputStream(pdf);
        ByteArrayOutputStream outbyte=new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outbyte);
        FileImage fileImage=dbFileStorageService.getFile(String.valueOf(11));
        Image img= Image.getInstance(fileImage.getData());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        document.add(chunk);
        document.add(img);
        document.close();
        FilePDFResponse file=new FilePDFResponse(fileName,outbyte.toByteArray(), MediaType.APPLICATION_PDF_VALUE);
        return file;
       // ResponseEntity<byte[]> responseEntity = new ResponseEntity<Byte[]>(outbyte.toByteArray(),);
       // return  responseEntity;
        /*return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(outbyte.toByteArray());*/
    }






}
