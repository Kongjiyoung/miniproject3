package com.many.miniproject1.apply;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {
    private final EntityManager em;

    public List<Apply> findAll() {
        Query query = em.createNativeQuery("select * from apply_tb() order by id desc", Apply.class);

        return query.getResultList();
    }

    public List<ApplyResponse.applyDetailDTO> findPostPass(int idx) {
        String q= """
                SELECT pt.id, pt.title, pt.career, pt.pay, pt.work_condition, pt.work_start_time, pt.work_end_time, pt.deadline, pt.task, pt.profile, pt.working_area, at.is_pass
                FROM post_tb pt
                INNER JOIN apply_tb at ON pt.id = at.post_id
                WHERE at.person_id = 1;
                """;
        Query query = em.createNativeQuery(q);
        List<Object[]> resultList = query.getResultList();

        List<ApplyResponse.applyDetailDTO> responseList = new ArrayList<>();
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
            String isPass = (String) row[11];

            ApplyResponse.applyDetailDTO responseDTO = new ApplyResponse.applyDetailDTO();
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
            responseDTO.setIsPass(isPass);

            responseList.add(responseDTO);
        }
        return responseList;
    }

    public List<ApplyResponse.applyDetailDTO> findResumePass(int idx) {
        String q= """
                SELECT rt.*, at.is_pass FROM resume_tb rt inner join apply_tb at on rt.id=at.resume_id where at.company_id=?;
                """;
        Query query = em.createNativeQuery(q);

        List<Object[]> resultList = query.getResultList();

        List<ApplyResponse.applyDetailDTO> responseList = new ArrayList<>();
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
            String isPass = (String) row[11];

            ApplyResponse.applyDetailDTO responseDTO = new ApplyResponse.applyDetailDTO();
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
            responseDTO.setIsPass(isPass);

            responseList.add(responseDTO);
        }
        return responseList;
    }
    @Transactional
    public void save(ApplyRequest.SaveDTO requestDTO) {
        Query query = em.createNativeQuery("INSERT INTO Apply_tb(resume_id, post_id, company_id, person_id, is_pass, created_at) VALUES (?,?,?,?,?,now())");
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getPostId());
        query.setParameter(3, requestDTO.getCompanyId());
        query.setParameter(4, requestDTO.getPersonId());
        query.setParameter(5, requestDTO.getIsPass());


        query.executeUpdate();
    }

    @Transactional
    public void update(ApplyRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update apply_tb set where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from apply_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }
}