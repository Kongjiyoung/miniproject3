package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
        public void findById_test(){
            // given
        int id = 1;
            // when
        User user = userQueryRepository.findById(id);
        System.out.println("findById_test: "+ user);
            // then
        Assertions.assertThat(user.getUsername()).isEqualTo("captain_kong");
        Assertions.assertThat(user.getName()).isEqualTo("공지영");
        }
}
