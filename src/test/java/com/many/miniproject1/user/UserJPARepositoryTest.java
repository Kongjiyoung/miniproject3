package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

//@Import(UserJPARepository.class)
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "captain_kong";
        String password = "1234";
        // when
        User user = userJPARepository.findByUsernameAndPassword(username, password);
        System.out.println("findByUsernameAndPassword_test: " + user);
        // then
        Assertions.assertThat(user.getAddress()).isEqualTo("부산광역시");
    }
}
