package com.many.miniproject1.apply;


import com.many.miniproject1._core.errors.exception.Exception400;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepository;
    @Autowired
    private EntityManager em;
    
    @Test
    public void findByPostIdJoinPostAndSkillAndUser_test(){
        // given
        int postid=1;
        int resumeUserId=1;
        // when
        Apply apply=applyJPARepository.findByPostIdJoinPostAndSkillAndUser(postid,resumeUserId).get();

        // then
        Assertions.assertThat(apply.getIsPass()).isEqualTo("불합격");
    }
}
