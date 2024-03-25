package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;



@DataJpaTest
public class ResumeJPARepositoryTest {
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;


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

    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Resume resume = Resume.builder()
                .user(sessionUser)
                .title("title")
                .profile("profilePath")
                .introduce("introduce")
                .career("career")
                .simpleIntroduce("simpleIntroduce")
                .build();
        // when
        resumeJPARepository.save(resume);

        // then
        System.out.println("save_test = " + resume.getId());
    }
}
