package com.many.miniproject1.offer;

import com.many.miniproject1.apply.ApplyController;
import com.many.miniproject1.apply.ApplyRepository;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.main.MainResponse;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeRepository;
import com.many.miniproject1.skill.SkillRepository;
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
    private final ApplyRepository applyRepository;

    @PostMapping("/company/offers/delete")
    public void delete(@RequestParam int id,HttpServletRequest request){
             offerRepository.delete(id);
             request.setAttribute("offerId",id);
    }

    // 제안한 이력서 상세보기
    @GetMapping("/company/offer/{id}/detail")
    public String search(HttpServletRequest request, @PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferResumeDetailDTO resumeDTO = offerRepository.companyOfferResumeDetail(sessionUser.getId(),id);
        List<String> skills = skillRepository.findByResumeId(id);
        OfferResponse.OfferResumeDetailPlusSkillDTO resumeSkill = new OfferResponse.OfferResumeDetailPlusSkillDTO(resumeDTO, skills);
        request.setAttribute("resume", resumeSkill);
        return "company/mypageResumeDetail";
    }
    // 제안한 이력서 제거
    @PostMapping("/company/offetr/{id}/detail/delete")
    public void offerDelete(@PathVariable int id, HttpServletRequest request){
        offerRepository.offerDelete(id);
        request.setAttribute("deleteByOfferId",id);
    }

    @GetMapping("/v")
    public String mattewEdit() {

        return "/company/mattewEdit";
    }

    // company의 offers 관리
    // skill 만 불러오면 되나.?
    @GetMapping("/company/offers")
    public String personPost(HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");
        List<OfferResponse.OfferResumeDTO> companyOfferList = offerRepository.personFindAllOffer(sessionUser.getId());

        ArrayList<OfferResponse.OfferResumeSkillDTO> cResumeSkillList = new ArrayList<>();
        for(int i =0 ; i<companyOfferList.size(); i++){
            List<String> skills = skillRepository.findByResumeId(companyOfferList.get(i).getId());
            System.out.println("🚆🎎"+skills);
            OfferResponse.OfferResumeDTO resume = companyOfferList.get(i);
            System.out.println("✨✨"+resume);

            cResumeSkillList.add(new OfferResponse.OfferResumeSkillDTO(resume, skills));
            System.out.println(cResumeSkillList.get(i));
        }
        request.setAttribute("cResumeSkillList", cResumeSkillList);

        return "company/offers";
    }

    // person의 offers 관리
    @GetMapping("/person/offerEmails")
    public String getOfferById( HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");
        // ot.company_id를 찾지 못함
        List<OfferResponse.OfferBoardDTO> responseDTO = (List<OfferResponse.OfferBoardDTO>) offerRepository.findCompanyOffersWithId(sessionUser.getId());
        System.out.println(responseDTO);

        request.setAttribute("offerList", responseDTO);

        return "person/offerEmails";
    }


    // 🚧🚧🚧🚧🚧email대신 공고 보내기로 수정🚧🚧🚧🚧🚧
//    @GetMapping("/person/offer/detail/{id}")
//    public String personPostDetail(@PathVariable int id) {
//        return "company/offerEmailDetail";
//    }

    @GetMapping("/company/updateInfoForm")
    public String FINDOFFER() {
        return "company/updateInfoForm";
    }

    @GetMapping("/company/offerEmailForm/")
    public String pers() {
        return "company/offerEmailForm";
    }


}