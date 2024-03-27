package com.many.miniproject1.Post;


import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostService;
import com.many.miniproject1.resume.*;
import com.many.miniproject1.user.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PostJPARepositoryTest {
    @Autowired
    private PostJPARepository postJPARepository;
    @Autowired
    private EntityManager em;
//    @Autowired
//    private PostService postService;

    @Test
    public void getPostList_test(){
        // given
        int id=14;

        // when
        List<Post> postList=postJPARepository.findByUserIdJoinSkillAndUser(id);
        // then
        Assertions.assertThat(postList.get(1).getId()).isEqualTo(1);
    }

}
