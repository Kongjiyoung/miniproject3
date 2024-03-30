package com.many.miniproject1.scrap;


import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
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
    @Autowired
    private OfferJPARepository offerJPARepository;
    @Autowired
    private PostJPARepository postJPARepository;

//    @Test
//    public void findByPostIdJoinskills_test() {
//        // given
//        int id = 1;
//
//        // when
//        List<Scrap> scrapList = scrapJPARepository.findByPostIdJoinskills(id);
//        System.out.println("findByPostIdJoinskills_test :" + scrapList);
//        System.out.println("스킬리스트:" + scrapList.get(id).getPost().getSkillList());
//        // then
//        // assertThat(scrapList.get(1).getId()).isEqualTo(2);
//    }
  
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

    @Test
    public void sendPostToResume_test(){
        // given
        int id = 1;
        int postId = 1;

        // when
        Scrap scrap = scrapJPARepository.findById(id)
                .orElseThrow(() -> new Exception401("++"));
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception401("++"));
        OfferRequest.ScrapOfferDTO scrapOfferDTO = new OfferRequest.ScrapOfferDTO(scrap.getResume(), post);
        Offer offer = offerJPARepository.save(scrapOfferDTO.toEntity());

        // then
//        assertThat(offer.);
        System.out.println("👩👴🧓👱‍♀️👼🎅👸🤴승호" + offer);
    }

    @Test
    public void findByIdJoinPostAndSkill_test() {
        // given
        int scrapId = 28;

        // when
        Scrap scrap = scrapJPARepository.findByScrapIdJoinPostAndSkill(scrapId);

        // then
        assertThat(scrap.getId()).isEqualTo(28);
        assertThat(scrap.getPost().getTitle()).isEqualTo("데이터 분석가");
        assertThat(scrap.getPost().getSkillList().size()).isEqualTo(3);
    }
}
