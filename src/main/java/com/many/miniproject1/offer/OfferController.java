package com.many.miniproject1.offer;

import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    // 제안한 이력서 상세보기
    @GetMapping("/person/offer/post/detail/{id}")
    public String personOfferDetail(HttpServletRequest request, @PathVariable int id) {
        Offer offer = offerService.offerDetail(id);
        request.setAttribute("offer", offer);
        return "person/offer-post-detail";
    }

    // person의 offers 관리
    @GetMapping("/person/offers")
    public String personOffers(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Offer> offerList = offerService.personOffers(sessionUser.getId());
        request.setAttribute("offerList", offerList);
        return "person/offers";
    }


    // company의 offers 관리
    // skill 만 불러오면 되나.?
    @GetMapping("/company/offers")
    public String personPost(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Offer> offerList = offerService.personPost(sessionUser.getId());
        System.out.println(offerList);
        request.setAttribute("offerList", offerList);
        return "company/offers";
    }

    @GetMapping("/company/offer/{id}/detail")
    public String companyOfferDetail(HttpServletRequest request, @PathVariable int id) {
        Offer offer = offerService.companyOfferDetail(id);
        request.setAttribute("offer", offer);
        return "company/mypage-resume-detail";
    }

    // 제안한 이력서 DELETE (취소)
    @PostMapping("/company/offer/{id}/detail/delete")
    public String companyOfferDetailDelete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.deleteOffer(id);
        return "redirect:/company/offers";
    }


    @GetMapping("/company/update-info-form")
    public String noFinded() {
        return "company/update-info-form";
    }

    @GetMapping("/company/offer-form/")
    public String noFinded2() {
        return "company/offer-form";
    }


}