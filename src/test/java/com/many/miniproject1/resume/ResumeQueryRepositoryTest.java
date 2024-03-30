package com.many.miniproject1.resume;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(PostQueryRepository.class)
@DataJpaTest
public class ResumeQueryRepositoryTest {
    @Autowired
    ResumeQueryRepository resumeQueryRepository;
    @Autowired
    private EntityManager em;

}
