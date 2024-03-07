package com.many.miniproject1.offer;

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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class OfferController {
    private final OfferRepository offerRepository;
    private final HttpSession session;
    // 이력서/스킬 레파지토리 불러오기
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;

    // company의 offers 관리
    // skill 만 불러오면 되나.?
    @GetMapping("/company/offers")
    public String personPost(HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");
        List<Resume> companyOfferList = offerRepository.personFindAllOffer(sessionUser.getId());
        System.out.println("🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗🚗");

        // mustache 스킬 불러오기
        ArrayList<MainResponse.resumeDTO> cResumeSkillList = new ArrayList<>();
        for(int i =0 ; i<companyOfferList.size(); i++){
            List<String> skills = skillRepository.findByResumeId(companyOfferList.get(i).getId());
            Resume resume = (Resume) companyOfferList.get(i);
            cResumeSkillList.add(new MainResponse.resumeDTO(resume,skills));
            System.out.println(resume);
            System.out.println(skills);
            System.out.println(cResumeSkillList.get(i));
        }
        request.setAttribute("cResumeSkillList", cResumeSkillList);

        return "company/offers";
    }

    // person의 offers 관리
    @GetMapping("/person/offerEmails")
    public String getOfferById( HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");
                System.out.println("🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈🎈");
        // ot.company_id를 찾지 못함
        List<OfferResponse.OfferBoardDTO> responseDTO = (List<OfferResponse.OfferBoardDTO>) offerRepository.findCompanyOffersWithId(sessionUser.getId());
        System.out.println(responseDTO);

        request.setAttribute("offer", responseDTO);

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