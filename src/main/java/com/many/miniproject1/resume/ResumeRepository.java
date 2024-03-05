package com.many.miniproject1.resume;

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
public class ResumeRepository {
    private final EntityManager em;

    public List<Resume> findAll() {
        Query query = em.createNativeQuery("select * from resume_tb", Resume.class);

        return query.getResultList();
    }

    public ResumeResponse.DetailDTO findById(int id) {
        Query query = em.createNativeQuery("select * from resume_tb where id=?", Resume.class);
        query.setParameter(1, id);

        try {
            ResumeResponse.DetailDTO resume = (ResumeResponse.DetailDTO) query.getSingleResult();
            return resume;
        } catch (Exception e) {
            return null;
        }

//        ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO(resume);
    }



    // 이력서 insert 한번 하고 -> max id 값 받아서 -> 이력서ID
    // 스킬을 스킬테이블에 체크박스에 체크된만큼 insert(이력서ID) 하기
    @Transactional
    public int save(ResumeRequest.SaveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into resume_tb(id, person_id, title, profile, username, birth, tel, address, email, portfolio, introduce, career, simpleIntroduce, skill) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())");
        query.setParameter(1, id);
        query.setParameter(2, requestDTO.getPersonId());
        query.setParameter(3, requestDTO.getTitle());
        query.setParameter(4, requestDTO.getProfile());
        query.setParameter(5, requestDTO.getUsername());
        query.setParameter(6, requestDTO.getBirth());
        query.setParameter(7, requestDTO.getTel());
        query.setParameter(8, requestDTO.getAddress());
        query.setParameter(9, requestDTO.getEmail());
        query.setParameter(10, requestDTO.getPortfolio());
        query.setParameter(11, requestDTO.getIntroduce());
        query.setParameter(12, requestDTO.getCareer());
        query.setParameter(13, requestDTO.getSimpleIntroduce());
        query.setParameter(14, requestDTO.getSkill());

        query.executeUpdate();
        
        // max pk 받아서 리턴!!
        return 1; // 이력서 pk값
    }

    @Transactional
    public void update(int id, ResumeRequest.UpdateDTO requestDTO) {
        Query query = em.createNativeQuery("update resume_tb set person_id=?, title=?, profile=?, username=?, birth=?, tel=?, address=?, email=?, portfolio=?, introduce=?, career=?, simple_introduce=?, skill=? where id = ?");
        query.setParameter(1, requestDTO.getPersonId());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getProfile());
        query.setParameter(4, requestDTO.getUsername());
        query.setParameter(5, requestDTO.getBirth());
        query.setParameter(6, requestDTO.getTel());
        query.setParameter(7, requestDTO.getAddress());
        query.setParameter(8, requestDTO.getEmail());
        query.setParameter(9, requestDTO.getPortfolio());
        query.setParameter(10, requestDTO.getIntroduce());
        query.setParameter(11, requestDTO.getCareer());
        query.setParameter(12, requestDTO.getSimpleIntroduce());
        query.setParameter(13, requestDTO.getSkill());
        query.setParameter(14, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}