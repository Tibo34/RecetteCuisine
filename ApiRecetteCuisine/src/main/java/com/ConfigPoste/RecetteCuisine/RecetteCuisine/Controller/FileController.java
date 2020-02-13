package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Controller;


import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.FileImage;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.IngredientRepository;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.DBFileStorageService;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.FileStorageException;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.MyFileNotFoundException;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.services.UploadFileResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
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
    private DBFileStorageService dbFileStorageService;


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





}
