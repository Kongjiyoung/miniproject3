package com.many.miniproject1.resume;


import com.many.miniproject1.user.User;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    public void update_test() {
        // given
        // 업데이트할 이력서 정보를 가져옵니다.
        Optional<Resume> optionalResume = resumeJPARepository.findById(1);
        assertThat(optionalResume).isPresent(); // 가져온 이력서가 존재하는지 확인합니다.

        Resume originalResume = optionalResume.get(); // 가져온 이력서 객체를 얻습니다.

        // 변경할 내용을 설정합니다.
        //ResumeRequest.UpdateDTO updateDTO = new ResumeRequest.UpdateDTO();

        String newTitle = "12312323213";
        originalResume.setTitle(newTitle);

        // when
//      resumeService.update(1, updateDTO); // 변경된 이력서를 저장하여 업데이트합니다.
        em.flush();
        em.clear();

        // then
        Optional<Resume> updatedOptionalResume = resumeJPARepository.findById(1);
        assertThat(updatedOptionalResume).isPresent();

        Resume updatedResume = updatedOptionalResume.get();
        // 저장된 이력서를 다시 가져와서 변경된 내용이 올바르게 반영되었는지 확인합니다.
        assertThat(updatedResume.getTitle()).isEqualTo(newTitle);
        System.out.println("update_test: " + newTitle);

    }

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
    public void findByIdJoinSkillAndUser_test() {
        //given
      int resumeId=1;

        // when
        Resume resumeDetail = resumeJPARepository.findByIdJoinSkillAndUser(resumeId);

        // then
        assertThat(resumeDetail.getTitle()).isEqualTo("백엔드 개발자 공지영입니다.");
    }

    @Test
        public void findByUser_test(){
            // given
        int id = 1;
            // when
        List<Resume> resumeList = resumeJPARepository.findByUserId(id);
        System.out.println("test:: "+resumeList);
            // then
        }

    @Test
    public void findByResumeUserIdAndSessionUserId_test() {
        // given
        int id = 1;

        // when
        List<Resume> resumeList = resumeJPARepository.findBySessionUserId(1);

        // then
        assertThat(resumeList.size()).isEqualTo(3);
    }
}
