package com.many.miniproject1.main;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeResponse;
import com.many.miniproject1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainRepository {
    private final EntityManager em;

    public List<User> findAll() {
        Query query = em.createNativeQuery("select * from user_tb", User.class);

        return query.getResultList();
    }

    public List<Post> findPost(int id) {
        Query query = em.createNativeQuery("select * from post_tb where company_id=?", Post.class);
        query.setParameter(1, id);

        return query.getResultList();
    }

    public List<Resume> findResume(int id) {
        Query query = em.createNativeQuery("select * from resume_tb where person_id=?", Resume.class);
        query.setParameter(1, id);

        return query.getResultList();
    }

    public Integer findPersonId(int resumeId) {
        Query query = em.createNativeQuery("select person_id from resume_tb where  id=?");
        query.setParameter(1, resumeId);

        Integer personId = (Integer) query.getSingleResult();
        return personId;
    }
    public Integer findCompanyId(int id) {
        Query query = em.createNativeQuery("select company_id from post_tb where id=?");
        query.setParameter(1, id);

        Integer companyId = (Integer) query.getSingleResult();
        return companyId;
    }

    public List<ResumeResponse.DetailDTO> findAllResume() {
        Query query = em.createNativeQuery("SELECT u.email, u.username, u.tel, u.address, u.birth, r.id, r.person_id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, r.created_at FROM user_tb u INNER JOIN resume_tb r ON u.id = r.person_id ");

        List<Object[]> rows = query.getResultList();
        List<ResumeResponse.DetailDTO> result = new ArrayList<>();

        for (Object[] row : rows) {
            String username = (String) row[0];
            String birth = (String) row[1];
            String tel = (String) row[2];
            String address = (String) row[3];
            String email = (String) row[4];
            Integer id = (Integer) row[5];
            Integer personId = (Integer) row[6];
            String title = (String) row[7];
            String profile = (String) row[8];
            String portfolio = (String) row[9];
            String introduce = (String) row[10];
            String career = (String) row[11];
            String simpleIntroduce = (String) row[12];
            Timestamp createdAt = (Timestamp) row[13];

            ResumeResponse.DetailDTO responseDTO = new ResumeResponse.DetailDTO();
            responseDTO.setUsername(username);
            responseDTO.setBirth(birth);
            responseDTO.setTel(tel);
            responseDTO.setAddress(address);
            responseDTO.setEmail(email);
            responseDTO.setId(id);
            responseDTO.setPersonId(personId);
            responseDTO.setTitle(title);
            responseDTO.setProfile(profile);
            responseDTO.setPortfolio(portfolio);
            responseDTO.setIntroduce(introduce);
            responseDTO.setCareer(career);
            responseDTO.setSimpleIntroduce(simpleIntroduce);
            responseDTO.setCreatedAt(createdAt);

            result.add(responseDTO);
        }
        return result;
    }

}
