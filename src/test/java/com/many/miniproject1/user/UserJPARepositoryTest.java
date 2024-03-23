package com.many.miniproject1.user;

import jakarta.persistence.EntityManager;
<<<<<<< HEAD
=======
import org.assertj.core.api.Assertions;
>>>>>>> master
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

<<<<<<< HEAD
import java.util.Optional;

@Import(UserJPARepository.class)
=======
import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

//@Import(UserJPARepository.class)
>>>>>>> master
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;

    @Test
<<<<<<< HEAD
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
=======
    public void findByUsernameAndPassword_test() {
        // given
        String username = "captain_kong";
        String password = "1234";
        // when
        User user = userJPARepository.findByUsernameAndPassword(username, password).get();
        System.out.println("findByUsernameAndPassword_test: " + user);
        // then
        assertThat(user.getAddress()).isEqualTo("부산광역시");
    }

    @Test
    public void save_test() {
        //given
        User user = User.builder()
                .role("person")
                .username("ssar2")
                .name("최주호")
                .email("ssar2@nate.com")
                .birth(Date.valueOf("1876-02-01"))
                .tel("010-1234-5678")
                .address("부산시 부산진구")
                .password("1234")
                .build();

        // when
        userJPARepository.save(user);

        // then
        System.out.println("save_test: " + user);
        assertThat(user.getRole()).isEqualTo("person");
>>>>>>> master
    }
}
