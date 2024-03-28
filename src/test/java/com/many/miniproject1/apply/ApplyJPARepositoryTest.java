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
    public void findAllAppliesWithPostsAndSkills_test(){
        // given
        Integer id = 1;
        
        // when
        applyJPARepository.findAllAppliesWithPostsAndSkills(id);
        
        // then
        System.out.println("findAllAppliesWithPostsAndSkills_test ❤ : " + applyJPARepository.findAllAppliesWithPostsAndSkills(id));
    }
}
