package com.many.miniproject1.resume;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ResumeJPARepositoryTest {
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;

    
    @Test
    public void update_test(){
        // given
        // 업데이트할 이력서 정보를 가져옵니다.
        Optional<Resume> optionalResume = resumeJPARepository.findById(1);
        assertThat(optionalResume).isPresent(); // 가져온 이력서가 존재하는지 확인합니다.

        Resume resume = optionalResume.get(); // 가져온 이력서 객체를 얻습니다.

        // 변경할 내용을 설정합니다.
        resume.setTitle("Updated Title");
        resume.setPortfolio("Updated Portfolio");
        resume.setIntroduce("Updated Introduce");
        resume.setCareer("Updated Career");
        resume.setSimpleIntroduce("Updated Simple Introduce");

        // when
        resumeJPARepository.save(resume); // 변경된 이력서를 저장하여 업데이트합니다.
    
        // then
        // 저장된 이력서를 다시 가져와서 변경된 내용이 올바르게 반영되었는지 확인합니다.
        Optional<Resume> updatedOptionalResume = resumeJPARepository.findById(resume.getId());
        assertThat(updatedOptionalResume).isPresent();
        Resume updatedResume = updatedOptionalResume.get();

        assertThat(updatedResume.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedResume.getPortfolio()).isEqualTo("Updated Portfolio");
        assertThat(updatedResume.getIntroduce()).isEqualTo("Updated Introduce");
        assertThat(updatedResume.getCareer()).isEqualTo("Updated Career");
        assertThat(updatedResume.getSimpleIntroduce()).isEqualTo("Updated Simple Introduce");
    }
}
