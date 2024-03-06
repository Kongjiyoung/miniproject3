package com.many.miniproject1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public List<Resume> findAll() {
        Query query = em.createNativeQuery("select * from resume_tb", Resume.class);

        return query.getResultList();
    }

    public ResumeResponse.DetailDTO findById(int r_id) {
        try {
            Query query = em.createNativeQuery("select r.id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce from resume_tb r inner join user_tb u on r.person_id = u.id where r.id = ?");
            query.setParameter(1, r_id);

            Object[] row = (Object[]) query.getSingleResult();

            Integer id = (Integer) row[0];
            String title = (String) row[1];
            String profile = (String) row[2];
            String portfolio = (String) row[3];
            String introduce = (String) row[4];
            String career = (String) row[5];
            String simpleIntroduce = (String) row[6];

            ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO();
            responseDTO.setId(id);
            responseDTO.setTitle(title);
            responseDTO.setProfile(profile);
            responseDTO.setPortfolio(portfolio);
            responseDTO.setIntroduce(introduce);
            responseDTO.setCareer(career);
            responseDTO.setSimpleIntroduce(simpleIntroduce);

            return responseDTO;
        } catch (NoResultException e) {
            return null;
        }
    }


    // 이력서 insert 한번 하고 -> max id 값 받아서 -> 이력서ID
    // 스킬을 스킬테이블에 체크박스에 체크된만큼 insert(이력서ID) 하기
    @Transactional
    public int save(ResumeRequest.SaveDTO requestDTO) {
        String q = """
                      insert into resume_tb(person_id, title, profile, portfolio, introduce, career,
                      simple_introduce, created_at) 
                      values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());
            """;

        Query query = em.createNativeQuery(q);

        query.setParameter(1, requestDTO.getPersonId());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getProfile());
        query.setParameter(4, requestDTO.getPortfolio());
        query.setParameter(5, requestDTO.getIntroduce());
        query.setParameter(6, requestDTO.getCareer());
        query.setParameter(7, requestDTO.getSimpleIntroduce());
//        query.setParameter(13, requestDTO.getSkill());

        query.executeUpdate();

        // max pk 받아서 리턴!!
        return 1; // 이력서 pk값
    }

    @Transactional
    public void update(int id, ResumeRequest.UpdateDTO requestDTO) {
        Query query = em.createNativeQuery("update resume_tb set person_id=?, title=?, profile=?, portfolio=?, introduce=?, career=?, simple_introduce=?, skill=? where id = ?");
        query.setParameter(1, requestDTO.getPersonId());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getProfile());
        query.setParameter(4, requestDTO.getPortfolio());
        query.setParameter(5, requestDTO.getIntroduce());
        query.setParameter(6, requestDTO.getCareer());
        query.setParameter(7, requestDTO.getSimpleIntroduce());
        query.setParameter(8, requestDTO.getSkill());
        query.setParameter(9, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}