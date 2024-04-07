package com.many.miniproject1.offer;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.main.MainService;
import com.many.miniproject1.scrap.ScrapService;
import com.many.miniproject1.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    /////////////// 기업(오퍼를 보내는 입장)
    // 개인의 이력서로 보낸 제안 목록(공고 목록이 보임)
    @GetMapping("/api/company/my-page/offers")
    public ResponseEntity<?> companyOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<OfferResponse.CompanyOffersDTO> respDTO = offerService.companyOffers(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 개인의 이력서로 보낸 제안 디테일(공고 디테일이 보임)
    @GetMapping("/api/company/my-page/offers/{id}")
    public ResponseEntity<?> companyOfferDetail(@PathVariable Integer id) {
        OfferResponse.CompanyOfferDetailDTO respDTO = offerService.companyOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 제안 철회하기
    @DeleteMapping("/api/company/my-page/offers/{id}")
    public ResponseEntity<?> companyOfferDetailDelete(@PathVariable Integer id) {
        offerService.offerDelete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 메인 화면에 게시된 이력서에 제안하기
    @PostMapping("/api/main/company/resumes/{id}/offer")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id,@RequestBody OfferRequest.PostChoiceDTO reqDTO) {
        OfferResponse.OfferDTO respDTO = offerService.offerInMain(id, reqDTO.getPostChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩한 이력서에 제안하기
    @PostMapping("/api/company/my-page/scraps/{id}/offer")
    public ResponseEntity<?> companyScrapResume(@PathVariable Integer id,@RequestBody OfferRequest.PostChoiceDTO reqDTO) {
        OfferResponse.OfferDTO respDTO = offerService.offerInScrap(id, reqDTO.getPostChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    /////////////// 개인(오퍼를 받는 입장)
    // 내 이력서로 온 제안 목록(공고 목록)
    @GetMapping("/api/person/my-page/offers")
    public ResponseEntity<?> personOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<OfferResponse.PersonOffersDTO> respDTO = offerService.personOffers(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 내 이력서로 온 제안 디테일(공고 디테일)
    @GetMapping("/api/person/my-page/offers/{id}")
    public ResponseEntity<?> personOfferDetail(@PathVariable Integer id) {
        OfferResponse.PersonOfferDetailDTO respDTO = offerService.personOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}