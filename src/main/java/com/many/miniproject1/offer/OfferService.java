package com.many.miniproject1.offer;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.skill.SkillJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;
    private final OfferQueryRepository offerQueryRepository;

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
        return offerJPARepository.findByPostId(id);
    }


    public List<Offer> personOffers(Integer postId) {
        return offerJPARepository.findByPostIdJoinPost(postId);
    }
    public Offer companyOfferDetail(int id) {
        Offer offer = offerJPARepository.findByIdJoinResumeAndSkillAndUser(id);
        return offer;
    }
}
