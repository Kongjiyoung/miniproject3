package com.many.miniproject1.offer;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;

    public List<Offer> personPost(Integer userId) {
        offerJPARepository.findByUserId(userId);
        System.out.println(offerJPARepository.findByUserId(userId));
        return offerJPARepository.findByUserId(userId);
    }

    @Transactional
    public void deleteOffer(int id) {
        offerJPARepository.deleteById(id);
    }

    public Offer offerDetail(Integer id) {
        return offerJPARepository.findByIdWithPostAndSkillList(id);
    }


    public List<Offer> personOffers(Integer postId) {
        return offerJPARepository.findByPostIdJoinPost(postId);
    }
    public Offer companyOfferDetail(int id) {
        Offer offer = offerJPARepository.findByIdJoinResumeAndSkillAndUser(id);
        return offer;
    }
}
