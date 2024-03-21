package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepository.class)
@DataJpaTest
public class UserQueryRepositoryTest {
    @Autowired
    UserQueryRepository userQueryRepository;
    @Autowired
    private EntityManager em;
}
