package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserJPARepository.class)
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;
}
