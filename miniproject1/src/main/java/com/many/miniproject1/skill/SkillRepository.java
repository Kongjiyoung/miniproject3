package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
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
    public  List<String> findBySkillId(int id) {
        Query query = em.createNativeQuery("select skill_id from skill_tb where resume_id=?");
        query.setParameter(1, id);

        List<String> rows = query.getResultList();
        List<String> skillIds = new ArrayList<>();

        for (String row : rows) {
            skillIds.add(row);
        }

        return skillIds;
    }
}
