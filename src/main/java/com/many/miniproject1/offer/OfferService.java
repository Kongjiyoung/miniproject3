package com.many.miniproject1.offer;


import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostJPARepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeJPARepository;
import com.many.miniproject1.scrap.Scrap;
import com.many.miniproject1.scrap.ScrapJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final PostJPARepository postJPARepository;
    private final ResumeJPARepository resumeJPARepository;

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


    //메인에서 제안하기
    @jakarta.transaction.Transactional
    public OfferResponse.OfferDTO offerInMain(Integer resumeId, Integer postId) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("존재하지 않는 이력서입니다."));
        Post post = postJPARepository.findById(postId)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다."));
        OfferRequest.OfferDTO offerDTO = new OfferRequest.OfferDTO(resume, post);
        Offer offer = offerJPARepository.save(offerDTO.toEntity());
        return new OfferResponse.OfferDTO(offer);
    }

    //스크랩에서 제아하기
    @jakarta.transaction.Transactional
    public OfferResponse.OfferDTO offerInScrap(Integer resumeId, Integer postChoice){
        Scrap scrap = scrapJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("존재하지 않은 값입니다"));
        Post post = postJPARepository.findById(postChoice)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다!" + postChoice));
        OfferRequest.OfferDTO offerDTO = new OfferRequest.OfferDTO(scrap.getResume(), post);
        Offer offer = offerJPARepository.save(offerDTO.toEntity());

        return new OfferResponse.OfferDTO(offer);
    }
}
