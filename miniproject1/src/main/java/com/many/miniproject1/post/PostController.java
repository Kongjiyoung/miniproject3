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
    public String companyPosts() { // 이 페이지는 포스트들을 확인할 수 있는 페이지라 이름 변경했습니다.
        // 목적: 우리 회사에서 쓴 포스트들이 화면에 나와야 한다.(0)
        return "company/posts";
    }

    @GetMapping("/company/post/detail/{id}")
    public String companyPostDetailForm(@PathVariable int id) {
        // 목적1: 포스트 디테일 페이지를 불러온다.(0)
        return "company/postDetail";
    }

    @GetMapping("/company/post/saveForm")
    public String companyPostForm() {
        // 목적: 공고를 등록하는 페이지를 불러온다.(0)
        return "company/savePostForm";
    }

    // 이거 패스 다시 설정
    @PostMapping("/company/post/save")
    public String companySavePost() {
        // 목적: 공고를 저장하고 디테일 페이지를 보여준다.(0)
        return "redirect:/company/post";
    }

    @GetMapping("/company/post/detail/{id}/updateForm")
    public String companyUpdatePostForm(@PathVariable int id) {
        // 목적1: 공고 업데이트 폼을 불러온다.(0)

        return "company/updatePostForm";
    }

    @PostMapping("/company/post/detail/{id}/update")
    public String companyUpdatePost(@PathVariable int id) {
        // 목적1: 업데이트폼에서 수정하기 누르면 그 디테일의 수정된 모습을 디테일페이지에서 볼 수 있게 바뀌기.(안즉)
        return "redirect:/company/post/detail/"+id;
    }

    @PostMapping("/company/post/detail/{id}/delete")
    public String companyDeletePost(@PathVariable int id) {
        // 목적1: 삭제하기를 누르면 포스트가 삭제된 /company/posts로 이동하게 하기. 그런데 그것은 redirect:/company/post이다.
        return "redirect:/company/post";
    }
}