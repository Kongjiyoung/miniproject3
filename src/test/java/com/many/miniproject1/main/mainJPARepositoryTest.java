package com.many.miniproject1.main;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1.offer.Offer;
import com.many.miniproject1.offer.OfferJPARepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapJPARepository;
import com.many.miniproject1.scrap.ScrapRequest;
import com.many.miniproject1.user.User;
import com.many.miniproject1.user.UserJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class mainJPARepositoryTest {

    @Autowired
    private ScrapJPARepository scrapJPARepository;
    @Autowired
    private PostJPARepository postJPARepository;
    @Autowired
    private OfferJPARepository offerJPARepository;
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void sendPostToResume_test(){
        // given
        int id = 1;
        int postId = 1;

        // when
        Scrap scrap = scrapJPARepository.findById(id)
                .orElseThrow(() -> new Exception401("존재하지 않는 이력서..." + id));
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception401("존재하지 않는 공고입니다!" + postId));
        OfferRequest.ScrapOfferDTO scrapOfferDTO = new OfferRequest.ScrapOfferDTO(scrap.getResume(), post);
        Offer offer = offerJPARepository.save(scrapOfferDTO.toEntity());

        // then
        System.out.println("승호 : "+ offer);
    }

    @Test
    public void companyScrap_test(){
        // given
        int id = 1;
        int userId = 1;

        // when
        Resume resume = resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception401("존재하지 않는 이력서입니다...!" + id));
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new Exception401("띠용~?" + userId));
        ScrapRequest.MainScrapDTO mainScrapDTO = new ScrapRequest.MainScrapDTO(resume, user);
        Scrap scrap = scrapJPARepository.save(mainScrapDTO.toEntity());

        // then
        System.out.println("승호 : " + scrap);
    }
}
