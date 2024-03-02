package com.many.miniproject1.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    //회사 공고 관리
    @GetMapping("/company/post")
    public String companyPostForm() {
        return "company/posts";
    }

    @GetMapping("/company/post/detail/{id}")
    public String companyPostDetailForm() {
        return "company/postDetail";
    }

    @GetMapping("/company/post/detail/{id}/saveForm")
    public String companySavePostForm() {
        return "company/savePostForm";
    }

    @PostMapping("/company/post/detail/{id}/save")
    public String companySavePostResume() {
        return "redirect:/company/post/detail/{id}";
    }

    @GetMapping("/company/post/detail/{id}/updateForm")
    public String companyUpdatePostForm() {
        return "company/updatePostForm";
    }

    @PostMapping("/company/post/detail/{id}/update")
    public String companyUpdatePost() {
        return "redirect:/company/post/detail/{id}";
    }

    @PostMapping("/company/post/detail/{id}/delete")
    public String companyDeletePost() {
        return "redirect:/company/post";
    }

//    //메인 채용 공고
//    @GetMapping("/post")
//    public String postForm() {return "company/main";}
//
//    @GetMapping("/post/detail/{id}")
//    public String postDetailForm(@PathVariable int id) {
//        return "company/postDetail";
//    }

//    //맞춤 공고 - 개인용
//    @GetMapping("/matching/post")
//    public String matchingPostForm() {return "company/matching";}
//
//    @GetMapping("/matching/post/detail/{id}")
//    public String matchingPostDetailForm(@PathVariable int id) {
//        return "company/postDetail";
//    }
}