package com.many.miniproject1.offer;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    // 04-01
    // person의 offers 관리
    @GetMapping("/api/person/offers")
    public ResponseEntity<?> personOffers() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<OfferResponse.personOffersDTO> respDTO = offerService.personOffers(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // person의 offer 상세보기
    @GetMapping("/api/person/offers/{id}")
    public ResponseEntity<?> personOfferDetail(@PathVariable int id) {

        OfferResponse.personOfferDetailDTO respDTO = offerService.personOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // company의 offers 관리
    @GetMapping("/api/company/offers")
    public ResponseEntity<?> companyOffers() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<OfferResponse.companyOffersDTO> respDTO = offerService.companyOffers(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // 04-02
    // company의 offer 상세보기
    @GetMapping("/api/company/offers/{id}")
    public ResponseEntity<?> companyOfferDetail(@PathVariable int id) {

        OfferResponse.companyOfferDetailDTO respDTO = offerService.companyOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    // 제안한 이력서 DELETE (취소)
    @DeleteMapping("/api/company/offers/{id}")
    public ResponseEntity<?> companyOfferDetailDelete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.deleteOffer(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}