package com.many.miniproject1.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OfferController {
    //개인 제안 관리
    @GetMapping("/person/offer")
    public String personPost() {
        return "person/offerEmails";
    }

    //    제안 받은 이메일 디테일 머스태치가 없는 것으로 추정됨. 찾으면 알려주시고 공유해주세요. 꼭이요!!!
    @GetMapping("/person/offer/detail/{id}")
    public String personPostDetail(@PathVariable int id) {
        return "company/offerEmailDetail";
    }

    @GetMapping("/person/offerEmailForm/")
    public String pers() {
        return "company/offerEmailForm";
    }
}