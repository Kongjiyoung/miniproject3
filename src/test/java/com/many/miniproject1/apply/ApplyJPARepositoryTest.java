package com.many.miniproject1.apply;


import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.post.PostService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findById_test() {
        // given
        ApplyRequest.UpdateIsPass reqDTO = new ApplyRequest.UpdateIsPass();
        reqDTO.setId(1);

        // when
        Optional<Apply> apply = applyJPARepository.findById(reqDTO.getId());

        // then
        // System.out.println("findById_test: " + apply);
        assertThat(apply.get().getResume().getTitle()).isEqualTo("백엔드 개발자 공지영입니다.");
        assertThat(apply.get().getIsPass()).isEqualTo("합격");
    }
}
