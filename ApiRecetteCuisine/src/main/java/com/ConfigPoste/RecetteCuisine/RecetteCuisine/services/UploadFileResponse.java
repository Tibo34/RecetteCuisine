package com.ConfigPoste.RecetteCuisine.RecetteCuisine.services;

public class UploadFileResponse {

    private int id;
    private String fileName;
    private String uri;
    private String contentType;
    private long size;


    public UploadFileResponse(int idFile,String name, String fileDownloadUri, String content, long s) {
        fileName=name;
        uri=fileDownloadUri;
        contentType=content;
        size=s;
        id=idFile;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
