package com.many.miniproject1.scrap;

import com.many.miniproject1.apply.ApplyResponse;
import com.many.miniproject1.post.PostRepository;
import com.many.miniproject1.post.PostResponse;
import com.many.miniproject1.skill.SkillRepository;
import com.many.miniproject1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final HttpSession session;
    private final ScrapRepository scrapRepository;
    private final SkillRepository skillRepository;
    private final PostRepository postRepository;

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
        PostResponse.DetailDTO responseDTO = postRepository.findById(id);
        request.setAttribute("post", responseDTO);
        return "person/ScrapPostDetail";
    }

    @PostMapping("/person/scrap/{id}/detail/delete")
    public String personScrapDelete(@PathVariable int id) {
        scrapRepository.deleteByPostId(id);
        return "redirect:/company/scrap";
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
        return "company/ScrapResumeDetail";
    }

    @PostMapping("/company/scrap/{id}/detail/delete")
    public String companyScrapDelete(@PathVariable int id) {
        scrapRepository.deleteByResumeId(id);
        return "redirect:/company/scrap";
    }

}