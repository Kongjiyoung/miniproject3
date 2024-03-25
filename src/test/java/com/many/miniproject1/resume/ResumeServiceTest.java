package com.many.miniproject1.resume;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ResumeServiceTest {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private EntityManager em;

}
