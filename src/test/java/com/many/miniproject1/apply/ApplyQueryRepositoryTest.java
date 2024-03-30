package com.many.miniproject1.apply;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ApplyQueryRepository.class)
@DataJpaTest
public class ApplyQueryRepositoryTest {
    @Autowired
    ApplyQueryRepository applyQueryRepository;
    @Autowired
    private EntityManager em;

}
