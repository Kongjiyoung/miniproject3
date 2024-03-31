package com.many.miniproject1.post;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

//@Import(PostQueryRepository.class)
@DataJpaTest
public class PostQueryRepositoryTest {
//    @Autowired
//    PostQueryRepository postQueryRepository;
    @Autowired
    private EntityManager em;

}
