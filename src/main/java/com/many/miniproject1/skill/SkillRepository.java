package com.many.miniproject1.skill;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRequest;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.resume.ResumeResponse;
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

    public List<String> findByResumeId(int id) {

        Query query = em.createNativeQuery("select skill from skill_tb where resume_id=?");
        query.setParameter(1, id);

        List<String> rows = query.getResultList();
        List<String> skills = new ArrayList<>();

        for (String row : rows) {
            skills.add(row);
        }

        return skills;
    }

    public List<String> findByPostId(int id) {

        Query query = em.createNativeQuery("select skill from skill_tb where post_id=?");
        query.setParameter(1, id);

        List<String> rows = query.getResultList();
        List<String> skills = new ArrayList<>();

        for (String row : rows) {
            skills.add(row);
        }

        return skills;
    }


    @Transactional
    public void saveSkillsFromResume(List<ResumeResponse.skillDTO> skillDTOs){
        for (ResumeResponse.skillDTO requestDTO : skillDTOs) {
            Query query = em.createNativeQuery("insert into skill_tb(skill, resume_id, created_at) values (?, ?, now())");
            query.setParameter(1, requestDTO.getSkill());
            query.setParameter(2, requestDTO.getResumeId());

            query.executeUpdate();
        }
    }

    @Transactional
    public void saveSkillsFromPost(List<SkillRequest.SaveDTO> skillDTOs, int postId) {
        for (SkillRequest.SaveDTO requestDTO : skillDTOs) {
            Query query = em.createNativeQuery("insert into skill_tb(skill, post_id, created_at) values (?, ?, now())");
            query.setParameter(1, requestDTO.getSkill());
            query.setParameter(2, postId);

            query.executeUpdate();
        }
    }

    @Transactional
    public void resetSkill(int id) { // 포스트/이력서를 업데이트 할 때 기존 스킬들을 리셋해서 다시 체크하게 하기 위해서 사용하는 것이다.
        Query query = em.createNativeQuery("DELETE FROM skill_tb WHERE post_id=?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}