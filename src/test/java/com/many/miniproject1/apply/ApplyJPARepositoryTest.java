package com.many.miniproject1.apply;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findById_test() {
        // given
        ApplyRequest.UpdateIsPassDTO reqDTO = new ApplyRequest.UpdateIsPassDTO();
        reqDTO.setId(1);

        // when
        Optional<Apply> apply = applyJPARepository.findById(reqDTO.getId());

        // then
        // System.out.println("findById_test: " + apply);
        assertThat(apply.get().getResume().getTitle()).isEqualTo("백엔드 개발자 공지영입니다.");
        assertThat(apply.get().getIsPass()).isEqualTo("합격");
    }

    @Test
    public void findByPostIdJoinPostAndSkillAndUser_test() {
        // given
        int postid = 1;
        int resumeUserId = 1;
        // when
        Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postid, resumeUserId).get();

    }

    @Test
    public void findByUserIdJoinPost_test() {
        // given
        int id = 14;

        // when
        List<Apply> applyList = applyJPARepository.findByUserIdJoinPost(id);

        // then
        System.out.println("findByUserIdJoinPost_test: " + applyList);
        assertThat(applyList.get(1).getId()).isEqualTo(2);
        assertThat(applyList.size()).isEqualTo(4);
    }


    @Test
    public void findAllAppliesWithPostsAndSkills_test() {
        // given
        Integer id = 1;

        // when
        applyJPARepository.findAllAppliesWithPostsAndSkills(id);

        // then
        System.out.println("findAllAppliesWithPostsAndSkills_test ❤ : " + applyJPARepository.findAllAppliesWithPostsAndSkills(id));
    }

    @Test
    public void findByApply_test() {
        // given
        int id = 1;
        // when
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id);
        System.out.println("test: " + apply);

        // then

        assertThat(apply.getIsPass()).isEqualTo("합격");

    }

    @Test
    public void findByCompanyIdJoinResume_test() {
        //given
        int companyId = 14;

        // when
        List<Apply> applyList = applyJPARepository.findByCompanyIdJoinResume(companyId);

        // then
        assertThat(applyList.size()).isEqualTo(4);
        assertThat(applyList.getFirst().getIsPass()).isEqualTo("합격");
    }

    @Test
    public void findByPersonIdJoinPost_test() {
        //given
        int personId = 1;

        // when
        List<Apply> applyList = applyJPARepository.findByPersonIdJoinPost(personId);

        // then
        assertThat(applyList.size()).isEqualTo(6);
        assertThat(applyList.getFirst().getResume().getTitle()).isEqualTo("백엔드 개발자 공지영입니다.");
    }

    @Test
    public void findPostByApplyId_test() {
        // given
        int id = 1;
        // when
        Apply apply = applyJPARepository.findPostByApplyId(id);

        // then
        assertThat(apply.getPost().getCareer()).isEqualTo("미들 (4~8년)");
    }
}
