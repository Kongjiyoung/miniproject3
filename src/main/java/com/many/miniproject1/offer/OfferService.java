package com.many.miniproject1.offer;


import com.many.miniproject1._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;

    // TODO: Optional orElseThrow 추가

    // 개인 제안 목록
    @Transactional(readOnly = true)
    public List<OfferResponse.PersonOffersDTO> personOffers(int id) {
        List<Offer> personOffers = offerJPARepository.personFindAllOffers(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return personOffers.stream().map(offer -> new OfferResponse.PersonOffersDTO(offer)).toList();
    }

    // 개인이 제안(공고)상세보기
    @Transactional(readOnly = true)
    public OfferResponse.PersonOfferDetailDTO personOfferDetail(int id) {
        Offer offer = offerJPARepository.personFindByOfferId(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return new OfferResponse.PersonOfferDetailDTO(offer);
    }

    // 기업이 보낸 제안(이력서)들
    @Transactional(readOnly = true)
    public List<OfferResponse.CompanyOffersDTO> companyOffers(int id) {
        List<Offer> companyOffers = offerJPARepository.companyFindAllOffers(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return companyOffers.stream().map(offer -> new OfferResponse.CompanyOffersDTO(offer)).toList();
    }

    // 04-02 YSH
    // 기업의 제안(이력서) 상세보기
    @Transactional(readOnly = true)
    public OfferResponse.CompanyOfferDetailDTO companyOfferDetail(int id) {
        Offer offer = offerJPARepository.companyFindByOfferId(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return new OfferResponse.CompanyOfferDetailDTO(offer);
    }
    // 기업의 제안 취소
    @Transactional
    public void offerDelete (int offerId){
        offerJPARepository.deleteById(offerId);
    }

}
