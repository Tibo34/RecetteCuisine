package com.ConfigPoste.RecetteCuisine.RecetteCuisine.Model;


import javax.persistence.*;

@Entity
@Table(name = "Image")
public class FileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private int id;

    @Basic
    @Column(name="type")
    private String fileType;

    @Basic
    @Column(name = "nom", nullable = true, precision = 0)
    private String fileName;

    @Lob
    private byte[] data;

    public FileImage(){}

    public FileImage(String fileName,byte[] data,String type) {
        this.fileName = fileName;
        this.data = data;
        fileType=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
