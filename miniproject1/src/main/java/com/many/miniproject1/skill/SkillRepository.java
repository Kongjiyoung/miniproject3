package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillRepository {
    private final EntityManager em;

    public List<Skill> findAll() {
        Query query = em.createNativeQuery("select * from skill_tb", Skill.class);

        return query.getResultList();
    }

    public Skill findById(int id) {
        Query query = em.createNativeQuery("select * from skill_tb where id=?");
        query.setParameter(1, id);

        Skill skill = (Skill) query.getSingleResult();

        return skill;
    }
}