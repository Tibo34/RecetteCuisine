package com.ConfigPoste.RecetteCuisine.RecetteCuisine.services;

import java.util.Base64;

public class FilePDFResponse {

    private String fileName;
    private byte[] data;
    private String type;
    private String dataBase64;


    public FilePDFResponse(String fileName, byte[] data, String type) {
        this.fileName = fileName;
        this.data = data;
        this.type = type;
        dataBase64 = Base64.getEncoder().encodeToString(data);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
