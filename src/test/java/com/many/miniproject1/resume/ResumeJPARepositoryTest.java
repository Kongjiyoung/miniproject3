package com.many.miniproject1.resume;

import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ResumeJPARepositoryTest {
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void save_test() {
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


    @Test
    public void findById_test() {
        // given
        ResumeResponse.ResumeDetailDTO respDTO = new ResumeResponse.ResumeDetailDTO();
        respDTO.setId(1);

        // when
        Optional<Resume> resume = resumeJPARepository.findById(respDTO.getId());

        // then
        System.out.println("findById_test: " + resume);
    }

    @Test
    public void findByIdJoinSkillAndUser_test() {
        //given
        ResumeResponse.ResumeDetailDTO respDTO = new ResumeResponse.ResumeDetailDTO();
        respDTO.setId(1);

        // when
        Resume resumeDetail = resumeJPARepository.findByIdJoinSkillAndUser(respDTO.getId());

        // then
        System.out.println("findByIdJoinSkillAndUser_test/resumeDetail: " + resumeDetail);
        System.out.println("findByIdJoinSkillAndUser_test/resumeDetail.getUser(): " + resumeDetail.getUser());
        System.out.println("findByIdJoinSkillAndUser_test/resumeDetail.getSkillList(): " + resumeDetail.getSkillList());
    }
}
