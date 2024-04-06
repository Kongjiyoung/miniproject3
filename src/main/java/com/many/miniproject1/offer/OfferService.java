package com.many.miniproject1.offer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;

    // 개인 제안 목록
    @Transactional(readOnly = true)
    public List<OfferResponse.PersonOffersDTO> personOffers(int id) {
        List<Offer> personOffers = offerJPARepository.personFindAllOffers(id);

        return personOffers.stream().map(offer -> new OfferResponse.PersonOffersDTO(offer)).toList();
    }

    // 개인이 제안(공고)상세보기
    @Transactional(readOnly = true)
    public OfferResponse.PersonOfferDetailDTO personOfferDetail(int id) {
        Offer offer = offerJPARepository.personFindByOfferId(id);

        return new OfferResponse.PersonOfferDetailDTO(offer);
    }

    // 기업이 보낸 제안(이력서)들
    @Transactional(readOnly = true)
    public List<OfferResponse.CompanyOffersDTO> companyOffers(int id) {
        List<Offer> companyOffers = offerJPARepository.companyFindAllOffers(id);

        return companyOffers.stream().map(offer -> new OfferResponse.CompanyOffersDTO(offer)).toList();
    }

    // 04-02 YSH
    // 기업의 제안(이력서) 상세보기
    @Transactional(readOnly = true)
    public OfferResponse.CompanyOfferDetailDTO companyOfferDetail(int id) {
        Offer offer = offerJPARepository.companyFindByOfferId(id);

        return new OfferResponse.CompanyOfferDetailDTO(offer);
    }
    // 기업의 제안 취소
    @Transactional
    public void offerDelete (int offerId){
        offerJPARepository.deleteById(offerId);
    }

}
