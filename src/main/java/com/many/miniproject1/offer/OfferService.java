package com.many.miniproject1.offer;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;

    public List<Offer> companyOffers(Integer userId) {
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

    // 04-01 YSH
    public List<OfferResponse.personOffersDTO> personOffers() {
        List<Offer> personOfferList = offerJPARepository.personFindAllOffers(); // 나는 디티오 말고 그냥 엔티티로 뽑은 정보를 요따가 담았다.
        List<OfferResponse.personOffersDTO> personOffersDTOs = new ArrayList<>(); // 나는 위에서 뽑은 애들을 디티오 리스트에 담을 것이다.
        personOfferList.stream().map(offer -> {
            return personOffersDTOs.add(OfferResponse.personOffersDTO.builder()
                    .offer(offer)
                    .post(offer.getPost())
                    .user(offer.getPost().getUser())
                    .build());
        }).collect(Collectors.toList());
        return personOffersDTOs;
    }

    public Offer companyOfferDetail(int id) {
        Offer offer = offerJPARepository.findByIdJoinResumeAndSkillAndUser(id);
        return offer;
    }
}
