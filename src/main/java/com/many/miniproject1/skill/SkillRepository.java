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
    public  List<String> findByResumeId(int id) {

        Query query = em.createNativeQuery("select skill from skill_tb where resume_id=?");
        query.setParameter(1, id);

        List<String> rows = query.getResultList();
        List<String> skills = new ArrayList<>();

        for (String row : rows) {
            skills.add(row);
        }

        return skills;
    }

    public  List<String> findByPostId(int id) {

        Query query = em.createNativeQuery("select skill from skill_tb where post_id=?");
        query.setParameter(1, id);

        List<String> rows = query.getResultList();
        List<String> skills = new ArrayList<>();

        for (String row : rows) {
            skills.add(row);
        }

        return skills;
    }

//    public List<String> saveSkillFromPost(PostRequest.SaveDTO requestDTO){
//        Query query = em.createNativeQuery("insert into skill_tb(skill, post_id, created_at) values ('?', ?, now())");
//                // 저장할 게시물의 아이디를 받아서 포문을 돌려서 스킬을 저장한다. 일단 저장 하나를 하게 쿼리를 짜고 그걸 컨트롤러에서 스킬길이만큼 그거한다.
//        query.setParameter(1, requestDTO.)
//    }
}
