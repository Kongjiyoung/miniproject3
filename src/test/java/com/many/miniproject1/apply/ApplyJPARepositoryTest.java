package com.many.miniproject1.apply;


import com.many.miniproject1._core.errors.exception.Exception400;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostService;
import jakarta.persistence.EntityManager;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void findByUserIdJoinPost_test(){
        // given
        int id = 14;

        // when
        List<Apply> applyList = applyJPARepository.findByUserIdJoinPost(id);

        // then
        assertThat(applyList.get(1).getId()).isEqualTo(2);
    }
    

    @Test
    public void findAllAppliesWithPostsAndSkills_test(){
        // given
        Integer id = 1;
        
        // when
        applyJPARepository.findAllAppliesWithPostsAndSkills(id);
        
        // then
        System.out.println("findAllAppliesWithPostsAndSkills_test ❤ : " + applyJPARepository.findAllAppliesWithPostsAndSkills(id));
    }
    public void findByPostIdJoinPostAndSkillAndUser_test(){
        // given
        int resumeid = 1;
        int userid = 14;
        // when
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(resumeid, userid);
        System.out.println("test: " + apply);
        // then

        Assertions.assertThat(apply.getIsPass()).isEqualTo("불합격");

    }
        @Test
        public void findByPostIdJoinPostAndSkillAndUser_test () {
            // given
            int postid = 1;
            int resumeUserId = 1;
            // when
            Apply apply = applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postid, resumeUserId).get();

            // then
            Assertions.assertThat(apply.getIsPass()).isEqualTo("불합격");
        }
}
