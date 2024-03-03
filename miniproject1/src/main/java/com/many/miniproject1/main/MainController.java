package com.many.miniproject1.main;

import com.many.miniproject1.post.Post;
import com.many.miniproject1.post.PostRepository;
import com.many.miniproject1.resume.Resume;
import com.many.miniproject1.resume.ResumeRepository;
import com.many.miniproject1.skill.SkillRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final ResumeRepository resumeRepository;
    private final PostRepository postRepository;
    private final SkillRepository skillRepository;
    //메인 구직 공고
    @GetMapping("/company/main")
    public String resumeForm(HttpServletRequest request) {
        List<Resume> resumeList=resumeRepository.findAll();
        System.out.println(resumeList.size());


        ArrayList<MainResponse.resumeDTO> resumeSkillList=new ArrayList<>();
        for(int i =0 ; i<resumeList.size(); i++){
            List<String> skills=skillRepository.findByResumeId(resumeList.get(i).getId());
            System.out.println(skills);
            Resume resume=(Resume)resumeList.get(i);
            System.out.println(resume);
            resumeSkillList.add(new MainResponse.resumeDTO(resume,skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);
        return "company/main";
    }

    @GetMapping("/resume/detail/{id}")
    public String resumeDetailForm() {
        return "person/resumeDetail";
    }
    @PostMapping("/resume/detail/{id}/offer")
    public String companyResumeOffer() {
        return "redirect:/resume/detail/{id}";
    }
    @PostMapping("/resume/detail/{id}/scrap")
    public String companyResumeScrap() {
        return "redirect:/resume/detail/{id}";
    }
    //메인 채용 공고
    @GetMapping("/person/main")
    public String postForm(HttpServletRequest request) {
        List<Post> postList=postRepository.findAll();
        System.out.println(postList.size());
        request.setAttribute("postList", postList);
        return "person/main";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetailForm() {
        return "company/postDetail";
    }
    @PostMapping("/post/detail/{id}/apply")
    public String personPostApply() {
        return "redirect:/post/detail/{id}";
    }
    @PostMapping("/post/detail/{id}/scrap")
    public String personPostScrap() {
        return "redirect:/post/detail/{id}";
    }
    //맞춤 공고 - 기업이 보는 매칭 이력서
    @GetMapping("/company/matching")
    public String matchingResumeForm() {
        return "person/matching";
    }

    @GetMapping("/matching/resume/detail/{id}")
    public String matchingResumeDetailForm() {
        return "person/resumeDetail";
    }
    @PostMapping("/matching/resume/detail/{id}/offer")
    public String matchingCompanyResumeOffer() {
        return "redirect:/matching/resume/detail/{id}";
    }
    @PostMapping("/matching/resume/detail/{id}/scrap")
    public String matchingCompanyResumeScrap() {
        return "redirect:/matching/resume/detail/{id}";
    }
    //맞춤 공고 - 개인이 보는 매칭 공고
    @GetMapping("/person/matching")
    public String matchingPostForm() {
        return "company/matching";
    }

    @GetMapping("/matching/post/detail/{id}")
    public String matchingPostDetailForm() {
        return "company/postDetail";
    }
    @PostMapping("/matching/post/detail/{id}/apply")
    public String matchingPersonPostapply() {
        return "/matching/post/detail/{id}";
    }
    @PostMapping("/matching/post/detail/{id}/scrap")
    public String matchingPersonPostScrap() {
        return "redirect:/matching/post/detail/{id}";
    }
}