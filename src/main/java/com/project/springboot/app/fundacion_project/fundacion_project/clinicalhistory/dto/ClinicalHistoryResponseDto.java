package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto;

import java.time.LocalDate;

public class ClinicalHistoryResponseDto {

    private Long id;
    private String fileName;
    private String fileType;
    private LocalDate uploadDate;
    private String childDocument;
    

    


    public ClinicalHistoryResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public String getChildDocument() {
        return childDocument;
    }

    

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setChildDocument(String childDocument) {
        this.childDocument = childDocument;
    }

}
