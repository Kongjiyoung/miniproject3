package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@Import(UserJPARepository.class)
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByUsername_test(){
        String username = "captain_kong";
        userJPARepository.findByUsername(username);
    }
    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<User> userOP = userJPARepository.findById(id);

        if(userOP.isPresent()){
            User user = userOP.get();
            System.out.println("findById_test : "+user.getUsername());
        }

        // then
    }
}
