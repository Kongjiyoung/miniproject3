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
    @GetMapping("/company/main")
    public String resumeForm() {
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
    public String postForm() {
        return "company/resumes";
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