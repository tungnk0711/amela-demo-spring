package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.model.Image;
import com.codegym.repository.IImageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ImageRepository implements IImageRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Image> findAll() {
        TypedQuery<Image> query = em.createNamedQuery("findAllImages", Image.class);
        List<Image> images = query.getResultList();
        return images;

    }

    // demo store procedure
    @Override
    public void save(Image image) {
        StoredProcedureQuery spAddImage = em.createNamedStoredProcedureQuery("addImage");
        spAddImage.setParameter("fileName", image.getFileName());
        spAddImage.execute();
    }
}
