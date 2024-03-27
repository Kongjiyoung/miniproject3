package com.many.miniproject1.Post;

import com.many.miniproject1.post.PostQueryRepository;
import com.many.miniproject1.resume.ResumeQueryRepository;
import com.many.miniproject1.user.UserQueryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(PostQueryRepository.class)
@DataJpaTest
public class PostQueryRepositoryTest {
    @Autowired
    PostQueryRepository postQueryRepository;
    @Autowired
    private EntityManager em;

}
