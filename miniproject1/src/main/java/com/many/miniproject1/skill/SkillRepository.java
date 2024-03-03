package com.many.miniproject1.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillRepository {

    private final EntityManager em;
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
