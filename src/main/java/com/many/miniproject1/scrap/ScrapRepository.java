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
    public void saveResume(ScrapRequest.SaveResumeDTO requestDTO) {
        Query query = em.createNativeQuery("insert into scrap_tb(resume_id, company_id, created_at) values(?,?,now())");
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getCompanyId());

        query.executeUpdate();
    }
    @Transactional
    public void savePost(ScrapRequest.SavePostDTO requestDTO) {
        Query query = em.createNativeQuery("insert into scrap_tb(post_id, person_id, created_at) values(?,?,now())");
        query.setParameter(1, requestDTO.getPostId());
        query.setParameter(2, requestDTO.getPersonId());

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