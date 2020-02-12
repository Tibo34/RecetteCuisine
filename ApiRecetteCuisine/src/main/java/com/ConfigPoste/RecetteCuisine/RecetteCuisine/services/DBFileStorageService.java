package com.ConfigPoste.RecetteCuisine.RecetteCuisine.services;

import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model.FileImage;
import com.ConfigPoste.RecetteCuisine.RecetteCuisine.Repository.FileImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private FileImageRepository dbFileRepository;

    public FileImage storeFile(MultipartFile file) throws FileStorageException {
        // Normalize file name
         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        FileImage dbFile = null;
        try {
            dbFile = new FileImage(fileName, file.getBytes(),file.getContentType()
            );
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
        return dbFileRepository.save(dbFile);
    }

    public FileImage getFile(String fileId) throws MyFileNotFoundException {
        int id=Integer.parseInt(fileId);
        return dbFileRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
