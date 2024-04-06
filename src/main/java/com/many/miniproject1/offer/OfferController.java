package com.many.miniproject1.offer;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.main.MainService;
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

    // TODO: 일단 메인서비스 연결, 수정해야함
    @PostMapping("/api/company/my-page/offers") // @PostMapping("/api/resumes/{id}/offer")
    public ResponseEntity<?> companyResumeOffer(@RequestBody OfferRequest.OfferSaveDTO reqDTO) {
        reqDTO = mainService.sendPostToResume(reqDTO.getResumeId(), reqDTO.getPostId());
        
        return ResponseEntity.ok(new ApiUtil<>(reqDTO));
    }

    // TODO: 이거뭔데? 이거 이상함. 나중에 보기
//    @PostMapping("/api/company/offers") // @PostMapping("/api/company/scraps/{id}")
//    public ResponseEntity<?> companyScrapResume(@RequestBody ScrapRequest.ScrapResumeeDTO reqDTO) {
//        scrapService.sendPostToResume(reqDTO.getResumeId(), reqDTO.getCompanyId());
//        System.out.println(reqDTO);
//        OfferResponse.ChoiceDTO respDTO = scrapService.sendPostToResume(reqDTO.getResumeId(), reqDTO.getCompanyId());
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }
}