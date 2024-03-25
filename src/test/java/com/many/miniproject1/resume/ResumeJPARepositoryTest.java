package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ResumeJPARepositoryTest {
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;
        // when
        Resume resume = resumeJPARepository.findByIdJoinUser(id).get();
        // then
        System.out.println("findByIdJoinUser_test: " + resume.getUser().getName());
        System.out.println("findByIdJoinUser_test: " + resume.getTitle());
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Resume> resumeOP = resumeJPARepository.findById(id);
        if (resumeOP.isPresent()) {
            Resume resume = resumeOP.get();
            System.out.println("findById_test: " + resume.getTitle());
        }
        // then
    }
}
