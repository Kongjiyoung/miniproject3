package com.many.miniproject1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public List<Resume> findAll() {
        Query query = em.createNativeQuery("select * from resume_tb", Resume.class);

        return query.getResultList();
    }

    public Resume findById(int id) {
        Query query = em.createNativeQuery("select * from resume_tb where id=?");
        query.setParameter(1, id);

        Resume resume = (Resume) query.getSingleResult();

        return resume;
    }

    @Transactional
    public void save(ResumeRequest.SaveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into resume_tb() values()");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(ResumeRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update resume_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}