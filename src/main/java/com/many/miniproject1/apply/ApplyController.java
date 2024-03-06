package com.many.miniproject1.apply;

import com.many.miniproject1.main.MainResponse;
import com.many.miniproject1.post.Post;
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
public class ApplyController {
    private final HttpSession session;
    private final ApplyRepository applyRepository;
    private final SkillRepository skillRepository;
    //기업에서 받은 이력서 관리

    @GetMapping("/company/resume")
    public String companyResume() {

        return "company/companyResumes";
    }

    @GetMapping("/company/resume/detail")
    public String companyResumeDetail(@PathVariable int id) {
        return "company/appliedResumeDetail";
    }

    @PostMapping("/company/resume/detail/{id}/ispass")
    public String companyPass(@PathVariable int id) {
        return "redirect:/company/companyResumes";
    }

    //이력서 현황
    @GetMapping("/person/apply")
    public String personApply(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        List<ApplyResponse.applyDetailDTO> postList=applyRepository.findPostPass(sessionUser.getId());
        System.out.println("size="+postList.size());

        ArrayList<ApplyResponse.postIsPassDTO> postSkillList=new ArrayList<>();
        for(int i =0 ; i<postList.size(); i++){
            System.out.println("size="+postList.size());
            System.out.println(postList.get(i));
            List<String> skills=skillRepository.findByPostId(postList.get(i).getId());
            System.out.println("==============="+skills);
            ApplyResponse.applyDetailDTO post=postList.get(i);
            System.out.println("==============="+post);
            postSkillList.add(new ApplyResponse.postIsPassDTO(post,skills));
            System.out.println(postSkillList.get(i));
        }
        request.setAttribute("postSkillList", postSkillList);
        return "person/applies";
    }

//    //메인 채용 공고
//    @PostMapping("/post/detail/{id}/apply")
//    public String personPostApply(@PathVariable int id) {
//        return "redirect:/post/detail/"+id;
//    }
//
//    //맞춤 공고 - 개인용
//    @PostMapping("/matching/post/detail/{id}/apply")
//    public String matchingPersonPostApply(@PathVariable int id) {
//        return "matching/post/detail/"+id;
//    }
}
