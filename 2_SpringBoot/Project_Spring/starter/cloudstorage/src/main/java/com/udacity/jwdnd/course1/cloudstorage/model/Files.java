package com.udacity.jwdnd.course1.cloudstorage.model;

public class Files {
    private Integer fileId ;
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;
    private Integer userId;

    public Files(Integer fileId , String fileName, String contentType, String fileSize, Integer userId, byte[] fileData  ) {
        this.fileId = fileId;
        this.userId = userId;
        this.fileData = fileData;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.fileName = fileName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
