package com.many.miniproject1.offer;

import com.many.miniproject1._core.utils.ApiUtil;
import com.many.miniproject1.user.SessionUser;
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

    // person의 offers 관리
    @GetMapping("/api/person/offers")
    public ResponseEntity<?> personOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        System.out.println(sessionUser.getId());
        List<OfferResponse.PersonOffersDTO> respDTO = offerService.personOffers(sessionUser.getId());
        System.out.println(respDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // person의 offer 상세보기
    @GetMapping("/api/person/offers/{id}")
    public ResponseEntity<?> personOfferDetail(@PathVariable Integer id) {
        OfferResponse.PersonOfferDetailDTO respDTO = offerService.personOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // company의 offers 관리
    @GetMapping("/api/company/offers")
    public ResponseEntity<?> companyOffers() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        List<OfferResponse.CompanyOffersDTO> respDTO = offerService.companyOffers(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // 04-02
    // company의 offer 상세보기
    @GetMapping("/api/company/offers/{id}")
    public ResponseEntity<?> companyOfferDetail(@PathVariable Integer id) {
        OfferResponse.CompanyOfferDetailDTO respDTO = offerService.companyOfferDetail(id);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
    // company의 offer DELETE (취소)
    @DeleteMapping("/api/company/offers/{id}")
    public ResponseEntity<?> companyOfferDetailDelete(@PathVariable Integer id) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.offerDelete(id);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}