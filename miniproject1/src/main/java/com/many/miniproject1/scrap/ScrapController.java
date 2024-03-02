package com.many.miniproject1.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    //개인 채용 공고 스크랩
    @GetMapping("/person/scrap")
    public String personScrapForm() {
        return "person/scrap";
    }

    @GetMapping("/person/scrap/detail")
    public String personScrapDetailForm() {
        return "person/postDetail";
    }

    @PostMapping("/person/scrap/detail/{id}/delete")
    public String personScrapDelete(@PathVariable int id) {
        return "redirect:/company/scrap";
    }

    //기업 이력서 스크랩
    @GetMapping("/company/scrap")
    public String companyScrapForm() {
        return "company/scrap";
    }

    @GetMapping("/company/scrap/detail")
    public String companyScrapDetailForm() {
        return "company/appliedResumeDetail";
    }

    @PostMapping("/company/scrap/detail/{id}/delete")
    public String companyScrapDelete(@PathVariable int id) {
        return "redirect:/company/scrap";
    }

//    //메인 구직 공고
//    @PostMapping("/resume/detail/{id}/scrap")
//    public String companyResumeScrap(@PathVariable int id) {
//        return "redirect:/resume/detail/{id}";
//    }
//
//    //메인 채용 공고
//    @PostMapping("/post/detail/{id}/scrap")
//    public String personPostScrap(@PathVariable int id) {
//        return "redirect:/post/detail/{id}";
//    }
//
//    //맞춤 공고 - 기업용
//
//    @PostMapping("/matching/resume/detail/{id}/scrap")
//    public String matchingCompanyResumeScrap(@PathVariable int id) {
//        return "redirect:/matching/resume/detail/{id}";
//    }
//
//    //맞춤 공고 - 개인용
//    @PostMapping("/matching/post/detail/{id}/scrap")
//    public String matchingPersonPostScrap(@PathVariable int id) {
//        return "redirect:/matching/post/detail/{id}";
//    }
}