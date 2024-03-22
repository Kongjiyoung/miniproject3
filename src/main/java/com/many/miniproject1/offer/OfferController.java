package com.many.miniproject1.offer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class OfferController {



    // 👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻
    // 제안한 이력서 상세보기
    @GetMapping("/person/offer/post/detail/{id}")
    public String personOfferDetail(HttpServletRequest request, @PathVariable int id) {



        return "person/offer-post-detail";
    }

    // person의 offers 관리
    @GetMapping("/person/offers")
    public String personOffers( HttpServletRequest request) {


        return "person/offers";
    }

    // 🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇

    // company의 offers 관리
    // skill 만 불러오면 되나.?
    @GetMapping("/company/offers")
    public String personPost(HttpServletRequest request) {

        return "company/offers";
    }

    @GetMapping("/company/offer/{id}/detail")
    public String companyOfferDetail(HttpServletRequest request, @PathVariable int id) {

        return "company/mypage-resume-detail";
    }

    // 제안한 이력서 DELETE (취소)
    @PostMapping("/company/offer/{id}/detail/delete")
    public String companyOfferDetailDelete(@PathVariable int id, HttpServletRequest request){


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

    // email대신 공고 보내기로 수정
//    @GetMapping("/person/offer/detail/{id}")
//    public String personPostDetail(@PathVariable int id) {
//        return "company/offerEmailDetail";
//    }

    // 상세보기에서 삭제할 것이므로 필요 ❌
//    @PostMapping("/company/offers/delete")
//    public void delete(@RequestParam int id,HttpServletRequest request){
//             offerRepository.delete(id);
//             request.setAttribute("offerId",id);
//    }
}