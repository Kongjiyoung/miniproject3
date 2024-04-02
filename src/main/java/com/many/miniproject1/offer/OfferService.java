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

    // 04-01 YSH ~
    // 개인이 보는 제안(공고)들
    public List<OfferResponse.personOffersDTO> personOffers(int id) {
        List<Offer> personOfferList = offerJPARepository.personFindAllOffers(id); // 나는 디티오 말고 그냥 엔티티로 뽑은 정보를 요따가 담았다.
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
    // 회사가 제안을 보낸 제안(이력서)들
    public List<OfferResponse.companyOffersDTO> companyOffers(int id) {
        List<Offer> companyOffers = offerJPARepository.companyFindAllOffers(id);

        return companyOffers.stream().map(offer -> new OfferResponse.companyOffersDTO(offer)).toList();
    }

    // 개인이 제안(공고)상세보기
    public OfferResponse.personOfferDetailDTO personOfferDetail(int id) {
        Offer offer = offerJPARepository.personFindByOfferId(id);

        return new OfferResponse.personOfferDetailDTO(offer);
    }

    // 04-02 YSH
    public OfferResponse.companyOfferDetailDTO companyOfferDetail(int id) {
        Offer offer = offerJPARepository.companyFindByOfferId(id);

        return new OfferResponse.companyOfferDetailDTO(offer);
    }

    @Transactional
    public void deleteOffer(int id) {
        offerJPARepository.deleteById(id);
    }


}
