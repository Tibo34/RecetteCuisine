package com.ConfigPoste.RecetteCuisine.RecetteCuisine.services;

import java.io.IOException;

public class FileStorageException extends Exception {
    public FileStorageException(String s) {
        super(s);
    }

    public FileStorageException(String s, IOException e) {
        super(s,e);
    }
}
