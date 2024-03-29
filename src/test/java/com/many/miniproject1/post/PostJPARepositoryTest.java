package com.many.miniproject1.post;


import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PostJPARepositoryTest {
    @Autowired
    private PostJPARepository postJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void getPostList_test() {
        // given
        int id = 14;

        // when
        List<Post> postList = postJPARepository.findByUserIdJoinSkillAndUser(id);
        // then
        assertThat(postList.get(1).getId()).isEqualTo(1);
    }

    @Test
    public void postDetail_test() {
        // given
        int id = 1;

        // when
        Post post = postJPARepository.findByIdJoinSkillAndCompany(id);

        // then
        assertThat(post.getId()).isEqualTo(1);
    }

    @Test
    public void findById_test() {
        // given
        PostRequest.UpdateDTO reqDTO = new PostRequest.UpdateDTO();
        reqDTO.setId(1);

        // when
        Optional<Post> post = postJPARepository.findById(reqDTO.getId());

        // then
        post.ifPresent(value -> assertThat(value.getTitle()).isEqualTo("데이터 분석가"));
    }

    @Test
    public void findAllPost_test() {
        // given


        // when
        List<Post> postList = postJPARepository.findAllPost();

        // then
        System.out.println("findAllPost_test: " + postList);
        assertThat(postList.size()).isEqualTo(13);
    }
}
