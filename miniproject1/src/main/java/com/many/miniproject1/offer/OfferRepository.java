package com.many.miniproject1.offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OfferRepository {
    private final EntityManager em;

    public List<Offer> findAll() {
        Query query = em.createNativeQuery("select * from offer_tb", Offer.class);

        return query.getResultList();
    }

    public Offer findById(int id) {
        Query query = em.createNativeQuery("select * from offer_tb where id=?");
        query.setParameter(1, id);

        Offer offer = (Offer) query.getSingleResult();

        return offer;
    }

    @Transactional
    public void save(OfferRequest.SaveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into offer_tb() values()");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(OfferRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update offer_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from offer_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}
