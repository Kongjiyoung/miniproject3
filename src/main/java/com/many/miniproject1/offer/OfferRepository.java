package com.many.miniproject1.offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OfferRepository {
    private final EntityManager em;




    //공지영 작성 지우지말기 offer_tb 저장
    @Transactional
    public void save(OfferRequest.SaveDTO requestDTO) {
        Query query = em.createNativeQuery("INSERT INTO offer_tb(resume_id, post_id, company_id, person_id, created_at) VALUES (?,?,?,?,now())");
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getPostId());
        query.setParameter(3, requestDTO.getCompanyId());
        query.setParameter(4, requestDTO.getPersonId());


        query.executeUpdate();
    }

    @Transactional
    public void update(OfferRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("UPDATE offer_tb SET where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("DELETE FROM offer_tb WHERE id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }


}
