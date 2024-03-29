package com.many.miniproject1.scrap;


import com.many.miniproject1.offer.OfferJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;


@DataJpaTest
public class ScrapJPARepositoryTest {
    @Autowired
    private ScrapJPARepository scrapJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByPostIdJoinskills_test() {
        // given
        int id = 1;

        // when
        List<Scrap> scrapList = scrapJPARepository.findByPostIdJoinskills(id);
        System.out.println("findByPostIdJoinskills_test :" + scrapList);
        System.out.println("스킬리스트:" + scrapList.get(id).getPost().getSkillList());
        // then
        // assertThat(scrapList.get(1).getId()).isEqualTo(2);
    }
  
    @Test
    public void findByUserIdJoinSkill_test() {
        // given
        int resumeId = 1;
        // when
        List<Scrap> scrapList = scrapJPARepository.findByUserIdJoinSkillAndResume(resumeId);
        System.out.println("test:::" + scrapList);
        // then
    }

    @Test
    public void resumeDetail_test() {
        // given
        int userId = 14;
        int resumeId = 1;
        // when
        Optional<Scrap> scrap = scrapJPARepository.findByResumeIdAndSkillAndUser(userId,resumeId);
        System.out.println("test::: " + scrap);
        // then
    }
}
