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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OfferService {
    private final OfferJPARepository offerJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final PostJPARepository postJPARepository;
    private final ResumeJPARepository resumeJPARepository;

    // 개인 제안 목록
    public List<OfferResponse.PersonOffersDTO> personOffers(Integer offerId) {
        List<Offer> personOffers = offerJPARepository.personFindAllOffers(offerId)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));
        List<OfferResponse.PersonOffersDTO> personOffersDTOs = new ArrayList<>();

        personOffers.stream().map(offer -> {
            return personOffersDTOs.add(OfferResponse.PersonOffersDTO.builder()
                    .offer(offer)
                    .post(offer.getPost())
                    .skllList(offer.getPost().getSkillList())
                    .build());
        }).toList();

        return personOffersDTOs;
    }

    // 개인이 제안(공고)상세보기
    public OfferResponse.PersonOfferDetailDTO personOfferDetail(Integer id) {
        Offer offer = offerJPARepository.personFindByOfferId(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return new OfferResponse.PersonOfferDetailDTO(offer);
    }

    // 기업이 보낸 제안(이력서)들
    public List<OfferResponse.CompanyOffersDTO> companyOffers(Integer id) {
        List<Offer> companyOffers = offerJPARepository.companyFindAllOffers(id)
                .orElseThrow(() -> new Exception404("제안이 존재 하지 않습니다"));

        return companyOffers.stream().map(OfferResponse.CompanyOffersDTO::new).toList();
    }

    // 기업의 제안(이력서) 상세보기
    public OfferResponse.CompanyOfferDetailDTO companyOfferDetail(Integer id) {
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
    @Transactional
    public OfferResponse.OfferDTO offerInScrap(Integer resumeId, Integer postChoice){
        Scrap scrap = scrapJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("존재하지 않은 값입니다"));
        Post post = postJPARepository.findById(postChoice)
                .orElseThrow(() -> new Exception404("존재하지 않는 공고입니다"));
        OfferRequest.OfferDTO offerDTO = new OfferRequest.OfferDTO(scrap.getResume(), post);
        Offer offer = offerJPARepository.save(offerDTO.toEntity());

        return new OfferResponse.OfferDTO(offer);
    }
}
