package com.many.miniproject1.backup.offer;

import com.many.miniproject1.backup.post.PostResponse;
import com.many.miniproject1.backup.skill.SkillRepository;
import com.many.miniproject1.backup.post.PostRepository;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class OfferController {
    private final OfferRepository offerRepository;
    private final HttpSession session;
    // 이력서/스킬 레파지토리 불러오기
    private final SkillRepository skillRepository;
    private final PostRepository postRepository;


    // 👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻👩‍💻👨‍💻
    // 제안한 이력서 상세보기
    @GetMapping("/person/offer/post/detail/{id}")
    public String personOfferDetail(HttpServletRequest request, @PathVariable int id) {

        PostResponse.DetailDTO responseDTO = postRepository.findById(id);
        request.setAttribute("post", responseDTO);
        List<String> skillList = skillRepository.findByPostId(id);
        request.setAttribute("skillList", skillList);
        User user=postRepository.findCompanyName(id);
        request.setAttribute("user", user);
        return "person/offer-post-detail";
    }

    // person의 offers 관리
    @GetMapping("/person/offers")
    public String personOffers( HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");
        // ot.company_id를 찾지 못함
        List<OfferResponse.OfferBoardDTO> responseDTO = (List<OfferResponse.OfferBoardDTO>) offerRepository.findCompanyOffersWithId(sessionUser.getId());
        System.out.println(responseDTO);

        request.setAttribute("offerList", responseDTO);

        return "person/offers";
    }

    // 🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌆🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇🌇


    @GetMapping("/company/offer/{id}/detail")
    public String companyOfferDetail(HttpServletRequest request, @PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferResumeDetailDTO resumeDTO = offerRepository.companyOfferResumeDetail(sessionUser.getId(),id);
        List<String> skills = skillRepository.findByResumeId(id);
        OfferResponse.OfferResumeDetailPlusSkillDTO resumeSkill = new OfferResponse.OfferResumeDetailPlusSkillDTO(resumeDTO, skills);
        request.setAttribute("resume", resumeSkill);
        return "company/mypage-resume-detail";
    }

    // 제안한 이력서 DELETE (취소)
    @PostMapping("/company/offer/{id}/detail/delete")
    public String companyOfferDetailDelete(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerRepository.offerDelete(id, sessionUser.getId());
        return "redirect:/company/offers";
    }



    // 🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

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