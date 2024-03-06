package com.many.miniproject1.offer;

import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OfferController {
    private final OfferRepository offerRepository;
    private final HttpSession session;

    // company의 offers 관리
    @GetMapping("/company/offers")
    public String personPost(HttpServletRequest request) {
        User sessionUser=(User)session.getAttribute("sessionUser");
        // 반복하기

        List<Resume> companyOfferList = offerRepository.personFindAllOffer(sessionUser.getId());
        System.out.println(companyOfferList);
        request.setAttribute("companyOfferList", companyOfferList);

        return "company/offers";
    }

    // person의 offers 관리
    @GetMapping("/person/offerEmails/{id}")
    public String getOfferById(@PathVariable int id, HttpServletRequest request) {
        System.out.println("🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈");
        // ot.company_id를 찾지 못함
        OfferResponse.OfferBoardDTO responseDTO = offerRepository.findCompanyOffersWithId(1);
        System.out.println("1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣");

        request.setAttribute("offer", responseDTO);

//        List<Offer> personOfferList = offerRepository.personFindAllOffer();
//        request.setAttribute("personOfferList", personOfferList);

        return "person/offerEmails";
    }

    //    제안 받은 이메일 디테일 머스태치가 없는 것으로 추정됨. 찾으면 알려주시고 공유해주세요. 꼭이요!!!
//    @GetMapping("/person/offer/detail/{id}")
//    public String personPostDetail(@PathVariable int id) {
//        return "company/offerEmailDetail";
//    }
    @GetMapping("/company/updateInfoForm")
    public String FINDOFFER() {
        return "company/updateInfoForm";
    }

    @GetMapping("/person/offerEmailForm/")
    public String pers() {
        return "company/offerEmailForm";
    }


}