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
    public String companyResume(HttpServletRequest request) {
        User sessionUser=(User) session.getAttribute("sessionUser");

        List<Post> postList=applyRepository.findPost(sessionUser.getId());
        System.out.println(postList.size());


        ArrayList<MainResponse.postDTO> postSkillList=new ArrayList<>();
        for(int i =0 ; i<postList.size(); i++){
            List<String> skills=skillRepository.findByPostId(postList.get(i).getId());
            System.out.println(skills);
            Post post=(postList.get(i));
            System.out.println(post);
            postSkillList.add(new MainResponse.postDTO(post,skills));
            System.out.println(postSkillList.get(i));
        }
        request.setAttribute("sessionuser", sessionUser);
        request.setAttribute("postSkillList", postSkillList);
        return "company/companyResumes";
    }

    @GetMapping("/company/resume/detail/{id}")
    public String companyResumeDetail(@PathVariable int id) {
        return "company/appliedResumeDetail";
    }

    @PostMapping("/company/resume/detail/{id}/ispass")
    public String companyPass(@PathVariable int id) {
        return "redirect:/company/companyResumes";
    }

    //이력서 현황
    @GetMapping("/person/apply")
    public String personApply() {
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
