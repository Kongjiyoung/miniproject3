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

    public ResumeResponse.DetailDTO findById(int r_id) {
            Query query = em.createNativeQuery("select r.id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, u.username, u.birth, u.tel, u.address, u.email from resume_tb r inner join user_tb u on r.person_id = u.id where r.id = ?");
            query.setParameter(1, r_id);

            Object[] row = (Object[]) query.getSingleResult();

            Integer id = (Integer) row[0];
            String title = (String) row[1];
            String profile = (String) row[2];
            String portfolio = (String) row[3];
            String introduce = (String) row[4];
            String career = (String) row[5];
            String simpleIntroduce = (String) row[6];
            String username = (String) row[7];
            String birth = (String) row[8];
            String tel = (String) row[9];
            String address = (String) row[10];
            String email = (String) row[11];

            ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO();
            responseDTO.setId(id);
            responseDTO.setTitle(title);
            responseDTO.setProfile(profile);
            responseDTO.setPortfolio(portfolio);
            responseDTO.setIntroduce(introduce);
            responseDTO.setCareer(career);
            responseDTO.setSimpleIntroduce(simpleIntroduce);
            responseDTO.setUsername(username);
            responseDTO.setBirth(birth);
            responseDTO.setTel(tel);
            responseDTO.setAddress(address);
            responseDTO.setEmail(email);

            return responseDTO;
    }

    // 이력서 insert 한번 하고 -> max id 값 받아서 -> 이력서ID
    // 스킬을 스킬테이블에 체크박스에 체크된만큼 insert(이력서ID) 하기
    @Transactional
    public int save(ResumeRequest.SaveDTO requestDTO) {
        String q = """
                          insert into resume_tb(person_id, title, profile, portfolio, introduce, career,
                          simple_introduce, created_at) 
                          values (?, ?, ?, ?, ?, ?, ?, now());
                """;

        Query query = em.createNativeQuery(q);

        query.setParameter(1, requestDTO.getPersonId());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getProfile());
        query.setParameter(4, requestDTO.getPortfolio());
        query.setParameter(5, requestDTO.getIntroduce());
        query.setParameter(6, requestDTO.getCareer());
        query.setParameter(7, requestDTO.getSimpleIntroduce());

        query.executeUpdate();

        Query maxQquery = em.createNativeQuery("select max(id) from resume_tb");
        Integer resumeId = (Integer) maxQquery.getSingleResult();
        return resumeId;

        // max pk 받아서 리턴!!
        // return 이력서 pk값
    }

    public List<ResumeResponse.DetailDTO> findResume(int u_id) {
        Query query = em.createNativeQuery("SELECT u.email, u.username, u.tel, u.address, u.birth, r.id, r.person_id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, r.created_at FROM user_tb u INNER JOIN resume_tb r ON u.id = r.person_id where r.person_id=?");
        query.setParameter(1, u_id);

        List<Object[]> results = query.getResultList();
        List<ResumeResponse.DetailDTO> responseDTO = new ArrayList<>();

        for (Object[] result : results) {

            ResumeResponse.DetailDTO DTO = new ResumeResponse.DetailDTO();
            DTO.setUsername((String) result[0]);
            DTO.setBirth((String) result[1]);
            DTO.setTel((String) result[2]);
            DTO.setAddress((String) result[3]);
            DTO.setEmail((String) result[4]);
            DTO.setId((Integer) result[5]);
            DTO.setPersonId((Integer) result[6]);
            DTO.setTitle((String) result[7]);
            DTO.setProfile((String) result[8]);
            DTO.setPortfolio((String) result[9]);
            DTO.setIntroduce((String) result[10]);
            DTO.setCareer((String) result[11]);
            DTO.setSimpleIntroduce((String) result[12]);
            DTO.setCreatedAt((Timestamp) result[13]);

            responseDTO.add(DTO);
        }
        return responseDTO;
    }

    @Transactional
    public void update(int id, ResumeRequest.UpdateDTO requestDTO) {
        Query query = em.createNativeQuery("update resume_tb set title=?, profile=?, portfolio=?, introduce=?, career=?, simple_introduce=? where id = ?");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getProfile());
        query.setParameter(3, requestDTO.getPortfolio());
        query.setParameter(4, requestDTO.getIntroduce());
        query.setParameter(5, requestDTO.getCareer());
        query.setParameter(6, requestDTO.getSimpleIntroduce());
        query.setParameter(7, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}