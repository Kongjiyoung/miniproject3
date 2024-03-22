package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
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

    @Test
    public void findByUsernameAndPassword_test(){
        // given


        // when


        // then
    }
}
