package com.many.miniproject1.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplyController {

    //기업에서 받은 이력서 관리

    @GetMapping("/company/resume")
    public String companyResume() {
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
