package com.many.miniproject1.scrap;



import com.many.miniproject1.offer.OfferJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class ScrapJPARepositoryTest {
    @Autowired
    private ScrapJPARepository scrapJPARepository;
    @Autowired
    private EntityManager em;

    @Test
        public void findByUserIdJoinSkill_test(){
            // given
        int id = 1;
            // when
        List<Scrap> scrapList = scrapJPARepository.findByUserIdJoinSkillAndResume(id);
        System.out.println("test:::" + scrapList);
            // then
        }
}
