package com.many.miniproject1.scrap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScrapRepository {
    private final EntityManager em;

    public List<Scrap> findAll() {
        Query query = em.createNativeQuery("select * from scrap_tb", Scrap.class);

        return query.getResultList();
    }

    public Scrap findById(int id) {
        Query query = em.createNativeQuery("select * from scrap_tb where id=?");
        query.setParameter(1, id);

        Scrap scrap = (Scrap) query.getSingleResult();

        return scrap;
    }

    @Transactional
    public void save(ScrapRequest.SaveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into scrap_tb() values()");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(ScrapRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update scrap_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from scrap_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}