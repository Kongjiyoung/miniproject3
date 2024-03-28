package com.many.miniproject1.apply;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByUserIdJoinPost_test(){
        // given
        int id = 14;

        // when
        List<Apply> applyList = applyJPARepository.findByUserIdJoinPost(id);

        // then
        assertThat(applyList.get(1).getId()).isEqualTo(2);
    }

}
