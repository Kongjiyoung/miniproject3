package com.many.miniproject1.scrap;

import com.many.miniproject1.apply.ApplyRepository;
import com.many.miniproject1.apply.ApplyRequest;
import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.main.MainRepository;
import com.many.miniproject1.offer.OfferRepository;
import com.many.miniproject1.offer.OfferRequest;
import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRepository;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.resume.Resume;
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
public class ScrapController {
    private final HttpSession session;
    private final ScrapRepository scrapRepository;
    private final SkillRepository skillRepository;
    private final PostRepository postRepository;
    private final MainRepository mainRepository;
    private final ApplyRepository applyRepository;
    private final OfferRepository offerRepository;

    //개인 채용 공고 스크랩
    @GetMapping("/person/scrap")
    public String personScrapForm(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        List<ScrapResponse.ScarpPostDTO> postList=scrapRepository.findPost(sessionUser.getId());
        System.out.println("size="+postList.size());

        ArrayList<ScrapResponse.ScarpPostSkillDTO> postSkillList=new ArrayList<>();
        for(int i =0 ; i<postList.size(); i++){
            System.out.println("size="+postList.size());
            System.out.println(postList.get(i));
            List<String> skills=skillRepository.findByPostId(postList.get(i).getId());
            System.out.println("==============="+skills);
            ScrapResponse.ScarpPostDTO post=postList.get(i);
            System.out.println("==============="+post);
            postSkillList.add(new ScrapResponse.ScarpPostSkillDTO(post,skills));
            System.out.println(postSkillList.get(i));
        }
        request.setAttribute("sessionuser", sessionUser);
        request.setAttribute("postSkillList", postSkillList);
        return "person/scrap";
    }
    @GetMapping("/person/scrap/{id}/detail")
    public String personScrapDetailForm(@PathVariable int id, HttpServletRequest request) {
        //뷰내용 뿌리기
        PostResponse.DetailDTO responseDTO = postRepository.findById(id);
        request.setAttribute("post", responseDTO);

        //이력서 선택
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer personId = sessionUser.getId();
        System.out.println(personId);
        List<Resume> resumeList = mainRepository.findResume(personId);
        request.setAttribute("resumeList", resumeList);
        System.out.println(resumeList);

        User user=postRepository.findCompanyName(id);
        request.setAttribute("user", user);

        return "person/ScrapPostDetail";
    }

    @PostMapping("/person/scrap/{id}/detail/delete")
    public String personScrapDelete(@PathVariable int id) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        scrapRepository.deleteByPostId(id, sessionUser.getId());
        return "redirect:/person/scrap";
    }

    @PostMapping("/person/scrap/{id}/detail/apply")
    public String personPostApply(@PathVariable int id, @RequestParam("resumeChoice") Integer resumeChoice) {
        System.out.println("======================" + resumeChoice);
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer personId = sessionUser.getId();
        System.out.println(personId);

        ApplyRequest.SaveDTO saveDTO = new ApplyRequest.SaveDTO();
        saveDTO.setResumeId(resumeChoice);
        saveDTO.setPostId(id);
        saveDTO.setCompanyId(mainRepository.findCompanyId(id));
        saveDTO.setPersonId(personId);
        saveDTO.setIsPass("검토중");


        System.out.println("save : " + saveDTO);
        applyRepository.save(saveDTO);

        return "redirect:/person/scrap/{id}/detail";
    }
    //기업 이력서 스크랩
    @GetMapping("/company/scrap")
    public String companyScrapForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ScrapResponse.ScrapResumeDTO> resumeList = scrapRepository.findResumeList(sessionUser.getId());
        System.out.println(resumeList.size());

        ArrayList<ScrapResponse.ScrapResumeSkillDTO> resumeSkillList = new ArrayList<>();
        for (int i = 0; i < resumeList.size(); i++) {
            List<String> skills = skillRepository.findByResumeId(resumeList.get(i).getId());
            System.out.println(skills);
            ScrapResponse.ScrapResumeDTO resume = resumeList.get(i);
            System.out.println(resume);

            resumeSkillList.add(new ScrapResponse.ScrapResumeSkillDTO(resume, skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);
        return "company/scrap";
    }

    @GetMapping("/company/scrap/{id}/detail")
    public String companyScrapDetailForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ScrapResponse.ScrapResumeDTO resume = scrapRepository.findResume(sessionUser.getId(),id);
        List<String> skills = skillRepository.findByResumeId(id);
        ScrapResponse.ScrapResumeSkillDTO resumeSkill=new ScrapResponse.ScrapResumeSkillDTO(resume, skills);
        request.setAttribute("resume", resumeSkill);

        System.out.println(sessionUser);
        Integer companyId = sessionUser.getId();
        System.out.println(companyId);
        List<Post> postList = mainRepository.findPost(companyId);
        System.out.println(postList);
        request.setAttribute("postList", postList);

        return "company/ScrapResumeDetail";
    }

    @PostMapping("/company/scrap/{id}/detail/delete")
    public String companyScrapDelete(@PathVariable int id) {
        User sessionUser=(User) session.getAttribute("sessionUser");
        scrapRepository.deleteByResumeId(id,sessionUser.getId());
        return "redirect:/company/scrap";
    }

    @PostMapping("/company/scrap/{id}/detail/offer")
    public String companyResumeOffer(@PathVariable int id, @RequestParam("postChoice") Integer postChoice) {
        System.out.println("======================" + postChoice);
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(sessionUser);
        Integer companyId = sessionUser.getId();
        System.out.println(companyId);

        OfferRequest.SaveDTO saveDTO = new OfferRequest.SaveDTO();
        saveDTO.setResumeId(id);
        saveDTO.setPostId(postChoice);
        saveDTO.setCompanyId(companyId);
        saveDTO.setPersonId(mainRepository.findPersonId(id));


        System.out.println(saveDTO);
        offerRepository.save(saveDTO);

        return "redirect:/company/scrap/{id}/detail";
    }

}