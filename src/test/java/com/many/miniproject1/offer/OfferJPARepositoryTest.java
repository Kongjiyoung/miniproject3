package com.many.miniproject1.offer;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class OfferJPARepositoryTest {
    @Autowired
    private OfferJPARepository offerJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void findByIdJoinResumeAndSkillAndUser_test() {
        //given
        OfferResponse.OfferedResumeDetailDTO reqDTO = new OfferResponse.OfferedResumeDetailDTO();
        reqDTO.setId(1);
        // when
        Offer offedResumeDetail = offerJPARepository.findByIdJoinResumeAndSkillAndUser(reqDTO.getId());

        // then
        System.out.println("findByIdJoinResumeAndSkillAndUser_test: " + offedResumeDetail);
        System.out.println("유저: " + offedResumeDetail.getResume().getUser());
        System.out.println("스킬리스트: " + offedResumeDetail.getResume().getSkillList());
    }

    @Test
    public void personOffers_test() {
        // given
        int id = 1;
        // when
        List<Offer> offerList = offerJPARepository.findByPostIdJoinPost(id);
        System.out.println(offerList);
        // then
    }

}
