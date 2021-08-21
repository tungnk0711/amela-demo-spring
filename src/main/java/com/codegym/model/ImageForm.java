package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

    private Long id;
    private MultipartFile[] fileName;

    public ImageForm() {
    }

    public ImageForm(MultipartFile[] fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile[] getFileName() {
        return fileName;
    }

    public void setFileName(MultipartFile[] fileName) {
        this.fileName = fileName;
    }
}
