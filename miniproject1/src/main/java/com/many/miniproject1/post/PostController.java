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
    public String companyPostDetailForm(@PathVariable int id) {
        
//      리턴 return "company/postDetail/"+id;로 나중에 변경
        return "company/postDetail";
    }

    @GetMapping("/company/post/detail/{id}/saveForm")
    public String companySavePostForm(@PathVariable int id) {
        return "company/savePostForm";
    }

    @PostMapping("/company/post/detail/{id}/save")
    public String companySavePostResume(@PathVariable int id) {
//        포스트 디테일 페이지 만들기
        return "redirect:/company/postDetail";
//        return "redirect:/company/post/detail/{id}";
    }

    @GetMapping("/company/post/detail/{id}/updateForm")
    public String companyUpdatePostForm(@PathVariable int id) {
        return "company/updatePostForm";
    }

    @PostMapping("/company/post/detail/{id}/update")
    public String companyUpdatePost(@PathVariable int id) {

        //        포스트 디테일 페이지 만들기
        return "redirect:/company/postDetail";
//        return "redirect:/company/post/detail/{id}";
    }

    @PostMapping("/company/post/detail/{id}/delete")
    public String companyDeletePost(@PathVariable int id) {
        return "redirect:/company/post";
    }
}