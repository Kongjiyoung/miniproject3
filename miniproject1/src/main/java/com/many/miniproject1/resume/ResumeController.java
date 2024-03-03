package com.many.miniproject1.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeRepository resumeRepository;

    //개인 이력서 관리
    @GetMapping("/person/resume")
    public String personResumeForm(HttpServletRequest request) {
        List<Resume> resumeList= resumeRepository.findAll();

        request.setAttribute("resumeList", resumeList);
        return "person/resumes";
    }

    @GetMapping("/person/resume/detail/{id}")
    public String personResumeDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @GetMapping("/person/resume/detail/{id}/saveForm")
    public String personSaveResumeForm(@PathVariable int id) {
        return "person/saveResumeForm";
    }

    @PostMapping("/person/resume/detail/{id}/save")
    public String personSaveResume(@PathVariable int id) {
        return "redirect:/person/resume/detail/{id}";
    }

    @GetMapping("/person/resume/detail/{id}/updateForm")
    public String personUpdateResumeForm(@PathVariable int id) {
        return "person/updateResumeForm";
    }

    @PostMapping("/person/resume/detail/{id}/update")
    public String personUpdateResume(@PathVariable int id) {
        return "redirect:/person/resume/detail/{id}";
    }

    @PostMapping("/person/resume/detail/{id}/delete")
    public String personDeletePost(@PathVariable int id) {
        return "redirect:/person/resume";
    }

//    //메인 구직 공고
//    @GetMapping("/resume")
//    public String resumeForm() {return "person/main";}
//
//    @GetMapping("/resume/detail/{id}")
//    public String resumeDetailForm(@PathVariable int id) {
//        return "person/resumeDetail";
//    }
//
//    //맞춤 공고 - 기업용
//    @GetMapping("/matching/resume")
//    public String matchingResumeForm() {return "company/matching";}
//
//    @GetMapping("/matching/resume/detail/{id}")
//    public String matchingResumeDetailForm(@PathVariable int id) {
//        return "person/resumeDetail";
//    }
}