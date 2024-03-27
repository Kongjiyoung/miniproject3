package com.many.miniproject1.post;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PostJPARepositoryTest {
    @Autowired
    private PostJPARepository postJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void getPostList_test(){
        // given
        int id=14;

        // when
        List<Post> postList=postJPARepository.findByUserIdJoinSkillAndUser(id);
        // then
        assertThat(postList.get(1).getId()).isEqualTo(1);
    }

    @Test
    public void postDetail_test(){
        // given
        int id = 1;
        
        // when
        Post post = postJPARepository.findByIdJoinSkillAndCompany(id);
        
        // then
        assertThat(post.getId()).isEqualTo(1);
    }
}
