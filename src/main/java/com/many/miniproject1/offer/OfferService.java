package com.many.miniproject1.offer;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.apply.Apply;
import com.many.miniproject1.apply.ApplyJPARepository;
import com.many.miniproject1.skill.SkillJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;
    private final OfferQueryRepository offerQueryRepository;

    @Transactional
    public void deleteOffer(int sessionUserId,int id){
        offerJPARepository.deleteOfferByPostId(sessionUserId,id);
    }
}
