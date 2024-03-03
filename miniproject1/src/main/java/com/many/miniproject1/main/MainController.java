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
            List<String> skills=skillRepository.findBySkillId(resumeList.get(i).getId());
            System.out.println(skills);
            Resume resume=(Resume)resumeList.get(i);
            System.out.println(resume);
            resumeSkillList.add(new MainResponse.resumeDTO(resume,skills));
            System.out.println(resumeSkillList.get(i));
        }
        request.setAttribute("resumeSkillList", resumeSkillList);
        return "company/main";
    }

    @GetMapping("/company/main/detail/{id}")
    public String resumeDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/company/main/detail/{id}/apply")
    public String companyResumeOffer(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/company/main/detail/{id}/scrap")
    public String companyResumeScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //메인 채용 공고
    @GetMapping("/person/main")
    public String postForm(HttpServletRequest request) {
        List<Post> postList=postRepository.findAll();
        System.out.println(postList.size());
        request.setAttribute("postList", postList);
        return "person/main";
    }

    @GetMapping("/person/main/detail/{id}")
    public String postDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/person/main/detail/{id}/apply")
    public String personPostApply(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/person/main/detail/{id}/scrap")
    public String personPostScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //맞춤 공고 - 기업용
    @GetMapping("/company/matching")
    public String matchingResumeForm() {
        return "person/resumes";
    }

    @GetMapping("/company/matching/detail/{id}")
    public String matchingResumeDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/company/matching/detail/{id}/apply")
    public String matchingCompanyResumeOffer(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/company/matching/detail/{id}/scrap")
    public String matchingCompanyResumeScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //맞춤 공고 - 개인용
    @GetMapping("/person/matching")
    public String matchingPostForm() {
        return "company/resumes";
    }

    @GetMapping("/person/matching/detail/{id}")
    public String matchingPostDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/person/matching/detail/{id}/apply")
    public String matchingPersonPostApply(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/person/matching/detail/{id}/scrap")
    public String matchingPersonPostScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }
}