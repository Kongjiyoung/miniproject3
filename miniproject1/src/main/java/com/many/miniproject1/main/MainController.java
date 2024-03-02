package com.many.miniproject1.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class MainController {
    //메인 구직 공고
    @GetMapping("/resume")
    public String resumeForm() {
        return "person/resumes";
    }

    @GetMapping("/resume/detail/{id}")
    public String resumeDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/resume/detail/{id}/apply")
    public String companyResumeOffer(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/resume/detail/{id}/scrap")
    public String companyResumeScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //메인 채용 공고
    @GetMapping("/post")
    public String postForm() {
        return "company/resumes";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/post/detail/{id}/apply")
    public String personPostApply(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/post/detail/{id}/scrap")
    public String personPostScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //맞춤 공고 - 기업용
    @GetMapping("/matching/resume")
    public String matchingResumeForm() {
        return "person/resumes";
    }

    @GetMapping("/matching/resume/detail/{id}")
    public String matchingResumeDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/matching/resume/detail/{id}/apply")
    public String matchingCompanyResumeOffer(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/matching/resume/detail/{id}/scrap")
    public String matchingCompanyResumeScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    //맞춤 공고 - 개인용
    @GetMapping("/matching/post")
    public String matchingPostForm() {
        return "company/resumes";
    }

    @GetMapping("/matching/post/detail/{id}")
    public String matchingPostDetailForm(@PathVariable int id) {
        return "person/resumeDetail";
    }

    @PostMapping("/matching/post/detail/{id}/apply")
    public String matchingPersonPostApply(@PathVariable int id) {
        return "redirect:/person/resume";
    }

    @PostMapping("/matching/post/detail/{id}/scrap")
    public String matchingPersonPostScrap(@PathVariable int id) {
        return "redirect:/person/resume";
    }
}