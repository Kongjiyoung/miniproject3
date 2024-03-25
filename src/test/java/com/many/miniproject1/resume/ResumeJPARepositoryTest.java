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
    @Autowired
    private ResumeService resumeService;
    
    @Test
    public void update_test(){
        // given
        // 업데이트할 이력서 정보를 가져옵니다.
        Optional<Resume> optionalResume = resumeJPARepository.findById(1);
        assertThat(optionalResume).isPresent(); // 가져온 이력서가 존재하는지 확인합니다.

        Resume resume = optionalResume.get(); // 가져온 이력서 객체를 얻습니다.

        // 변경할 내용을 설정합니다.
        ResumeRequest.UpdateDTO updateDTO = new ResumeRequest.UpdateDTO();
        updateDTO.setTitle("12312323213");


        // when
        resumeService.update(1, updateDTO); // 변경된 이력서를 저장하여 업데이트합니다.
    
        // then
        // 저장된 이력서를 다시 가져와서 변경된 내용이 올바르게 반영되었는지 확인합니다.
        System.out.println("resume = " + resume);



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
