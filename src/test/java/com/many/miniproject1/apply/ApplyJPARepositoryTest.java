package com.many.miniproject1.apply;


import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostService;
import jakarta.persistence.EntityManager;
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
    public void findByResume_test() {
        // given
        int id = 1;
        // when
        Apply apply = applyJPARepository.findByResumeIdJoinSkillAndCompany(id);
        System.out.println("test: "+apply);
        // then
    }
}
