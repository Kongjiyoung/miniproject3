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
    private final ScrapService scrapService;
    private final MainService mainService;

    // person의 offers 관리
    @GetMapping("/api/person/my-page/offers")
    public ResponseEntity<?> personOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<OfferResponse.PersonOffersDTO> respDTO = offerService.personOffers(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // person의 offer 상세보기
    @GetMapping("/api/person/my-page/offers/{id}")
    public ResponseEntity<?> personOfferDetail(@PathVariable Integer id) {
        OfferResponse.PersonOfferDetailDTO respDTO = offerService.personOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // company의 offers 관리
    @GetMapping("/api/company/my-page/offers")
    public ResponseEntity<?> companyOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<OfferResponse.CompanyOffersDTO> respDTO = offerService.companyOffers(14);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 04-02
    // company의 offer 상세보기
    @GetMapping("/api/company/my-page/offers/{id}")
    public ResponseEntity<?> companyOfferDetail(@PathVariable int id) {
        OfferResponse.CompanyOfferDetailDTO respDTO = offerService.companyOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // company의 offer DELETE (취소)
    @DeleteMapping("/api/company/my-page/offers/{id}")
    public ResponseEntity<?> companyOfferDetailDelete(@PathVariable Integer id) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.offerDelete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 메인에서 제안하기
    @PostMapping("/api/main/company/resumes/{id}/offer")
    public ResponseEntity<?> companyResumeOffer(@PathVariable Integer id,@RequestBody OfferRequest.PostChoiceDTO reqDTO) {
        OfferResponse.OfferDTO respDTO = offerService.offerInMain(id, reqDTO.getPostChoice());
        
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 스크랩에서 제아하기
    @PostMapping("/api/company/my-page/scraps/{id}/offer")
    public ResponseEntity<?> companyScrapResume(@PathVariable Integer id,@RequestBody OfferRequest.PostChoiceDTO reqDTO) {
        OfferResponse.OfferDTO respDTO = offerService.offerInScrap(id, reqDTO.getPostChoice());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}