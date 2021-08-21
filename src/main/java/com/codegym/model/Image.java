package com.codegym.model;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "findAllImages",
        query = "select i from Image i")
@NamedStoredProcedureQuery(
        name = "addImage",
        procedureName = "spAddImage",
        parameters = {
                @StoredProcedureParameter(name = "fileName", mode = ParameterMode.IN, type = String.class)
        }
)
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String fileName;

    public Image() {
    }

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
