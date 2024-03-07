package com.many.miniproject1.scrap;

import com.many.miniproject1.apply.ApplyResponse;
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
public class ScrapRepository {
    private final EntityManager em;

    public List<Scrap> findAll() {
        Query query = em.createNativeQuery("select * from scrap_tb", Scrap.class);

        return query.getResultList();
    }

    public Scrap findById(int id) {
        Query query = em.createNativeQuery("select * from scrap_tb where id=?");
        query.setParameter(1, id);

        Scrap scrap = (Scrap) query.getSingleResult();

        return scrap;
    }

    @Transactional
    public void saveResume(ScrapRequest.SaveResumeDTO requestDTO) {
        Query query = em.createNativeQuery("insert into scrap_tb(resume_id, company_id, created_at) values(?,?,now())");
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getCompanyId());

        query.executeUpdate();
    }
    @Transactional
    public void savePost(ScrapRequest.SavePostDTO requestDTO) {
        Query query = em.createNativeQuery("insert into scrap_tb(post_id, person_id, created_at) values(?,?,now())");
        query.setParameter(1, requestDTO.getPostId());
        query.setParameter(2, requestDTO.getPersonId());

        query.executeUpdate();
    }

    @Transactional
    public void update(ScrapRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update scrap_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void deleteByResumeId(int id, int companyId) {
        Query query = em.createNativeQuery("delete from scrap_tb where resume_id = ? AND company_id=?");
        query.setParameter(1, id);
        query.setParameter(2, companyId);

        query.executeUpdate();
    }
    @Transactional
    public void deleteByPostId(int id, int personId) {
        Query query = em.createNativeQuery("delete from scrap_tb where post_id = ? AND person_id=?");
        query.setParameter(1, id);
        query.setParameter(2, personId);

        query.executeUpdate();
    }

    public List<ScrapResponse.ScarpPostDTO> findPost(Integer idx) {
            String q = """
                SELECT pt.id, pt.title, pt.career, pt.pay, pt.work_condition, pt.work_start_time, pt.work_end_time, pt.deadline, pt.task, pt.profile, pt.working_area
                FROM post_tb pt
                INNER JOIN scrap_tb st ON pt.id = st.post_id
                WHERE st.person_id =?
                """;

            Query query = em.createNativeQuery(q);
            query.setParameter(1, idx);
            List<Object[]> resultList = query.getResultList();

            List<ScrapResponse.ScarpPostDTO> responseList = new ArrayList<>();
            for (Object[] row : resultList) {
                Integer id = (Integer) row[0];
                String title = (String) row[1];
                String career = (String) row[2];
                String pay = (String) row[3];
                String workCondition = (String) row[4];
                String workStartTime = (String) row[5];
                String workEndTime = (String) row[6];
                String deadline = (String) row[7];
                String task = (String) row[8];
                String profile = (String) row[9];
                String workingArea = (String) row[10];

                ScrapResponse.ScarpPostDTO responseDTO = new ScrapResponse.ScarpPostDTO();
                responseDTO.setId(id);
                responseDTO.setTitle(title);
                responseDTO.setCareer(career);
                responseDTO.setPay(pay);
                responseDTO.setWorkCondition(workCondition);
                responseDTO.setWorkStartTime(workStartTime);
                responseDTO.setWorkEndTime(workEndTime);
                responseDTO.setDeadline(deadline);
                responseDTO.setTask(task);
                responseDTO.setProfile(profile);
                responseDTO.setWorkingArea(workingArea);
                responseList.add(responseDTO);
            }
            return responseList;
    }

    public List<ScrapResponse.ScrapResumeDTO> findResumeList(int idx) {
        String q = """
                SELECT u.email, u.username, u.tel, u.address, u.birth, r.id, r.person_id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, r.created_at
                FROM user_tb u
                INNER JOIN resume_tb r ON u.id = r.person_id
                INNER JOIN scrap_tb s ON r.id = s.resume_id
                WHERE s.company_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, idx);

        List<Object[]> rows = query.getResultList();
        List<ScrapResponse.ScrapResumeDTO> result = new ArrayList<>();

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

            ScrapResponse.ScrapResumeDTO responseDTO = new ScrapResponse.ScrapResumeDTO();
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

    public ScrapResponse.ScrapResumeDTO findResume(int companyId, int resumeId) {
        String q = """
                SELECT u.email, u.username, u.tel, u.address, u.birth, r.id, r.person_id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, r.created_at, a.is_Pass
                FROM user_tb u
                INNER JOIN resume_tb r ON u.id = r.person_id
                INNER JOIN apply_tb a ON r.id = a.resume_id
                WHERE a.company_id = ? AND a.id=?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, companyId);
        query.setParameter(2, resumeId);

        Object[] row = (Object[]) query.getSingleResult();

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

        ScrapResponse.ScrapResumeDTO responseDTO = new ScrapResponse.ScrapResumeDTO();
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

        return responseDTO;
    }
}