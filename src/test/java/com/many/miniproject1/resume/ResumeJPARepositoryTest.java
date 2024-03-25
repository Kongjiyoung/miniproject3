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



}
