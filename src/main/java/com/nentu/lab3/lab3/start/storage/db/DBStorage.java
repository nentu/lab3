package com.nentu.lab3.lab3.start.storage.db;

import com.nentu.lab3.lab3.start.main.CoordinateBean;
import com.nentu.lab3.lab3.start.storage.db.entity.CoordinateEntity;
import com.nentu.lab3.lab3.start.storage.interfaces.IStorage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBStorage implements IStorage<CoordinateBean> {
    private Map<String, String> persistanceConfigMap;

    public DBStorage(Map<String, String> persistanceConfigMap) {
        this.persistanceConfigMap = persistanceConfigMap;
    }

    public DBStorage() {
        this(new HashMap<>());
    }

    @Override
    public void add(CoordinateBean newItem) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default", persistanceConfigMap);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(newItem.getEntity());
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public List<CoordinateBean> getList() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default", persistanceConfigMap);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var listCoordinateEntity = entityManager.createQuery(
                "SELECT c FROM CoordinateEntity c").getResultList();

//        ArrayList<Coordinate> res = (ArrayList<Coordinate>) listCoordinateEntity.stream()
//                .map(e -> (Coordinate) Coordinate.fromEntity((CoordinateEntity) e))
//                .collect(Collectors.toCollection(() -> new ArrayList<Coordinate>())); //TODO
        List<CoordinateBean> res = new ArrayList<>();
        for (var i: listCoordinateEntity){
            res.add(CoordinateBean.fromEntity((CoordinateEntity) i));
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        res.sort((o1, o2)-> (int) (o1.getId() - o2.getId()));

        return res;
    }

    @Override
    public void clear() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default", persistanceConfigMap);

        // Создание EntityManager
        EntityManager em = emf.createEntityManager();

        // Очистка таблицы
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM CoordinateEntity ");
        int deletedCount = query.executeUpdate();
        em.getTransaction().commit();

        // Закрытие EntityManager и EntityManagerFactory
        em.close();
        emf.close();
    }


    @Override
    public List<CoordinateBean> getReversedList() {
        var list = getList();

        var size = list.size();
        var tList = new ArrayList<CoordinateBean>();
        for (int i = 0; i < size; i++) {
            tList.add(list.get(size - i - 1));
        }
        return tList;

    }
}
