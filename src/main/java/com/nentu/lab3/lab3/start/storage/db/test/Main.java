package com.nentu.lab3.lab3.start.storage.db.test;

import com.nentu.lab3.lab3.start.main.CoordinateBean;
import com.nentu.lab3.lab3.start.storage.db.entity.CoordinateEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;

public class Main {
    @PersistenceContext
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var listCoordinateEntity= entityManager.createQuery(
                "SELECT c FROM CoordinateEntity c").getResultList();

        ArrayList<CoordinateBean> res = new ArrayList<CoordinateBean>();

        listCoordinateEntity.forEach((e) -> res.add((CoordinateBean) CoordinateBean.fromEntity((CoordinateEntity) e)));

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}