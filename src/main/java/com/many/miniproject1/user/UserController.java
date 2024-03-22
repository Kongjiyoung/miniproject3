package com.many.miniproject1.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    // 회사 회원가입
    @GetMapping("/company/join-form")
    public String companyJoinForm() {
        return "company/join-form";
    }

    @PostMapping("/company/join")
    public String companyJoin() {
        return "redirect:/company/login-form";
    }

    // 회사 로그인
    @GetMapping("/company/login-form")
    public String companyLoginForm() {
        return "company/login-form";
    }

    @PostMapping("/company/login")
    public String companyLogin(UserRequest.LoginDTO requestDTO) {
        session.setAttribute("sessionUser", userService.로그인(requestDTO));
        return "redirect:/company/main";
    }


    // 개인 로그인 회원가입
    @GetMapping("/person/join-form")
    public String personJoinForm() {
        return "person/join-form";
    }

    @PostMapping("/person/join")
    public String personJoin() {
        return "redirect:/person/login-form";
    }

    // 개인 로그인 회원가입
    @GetMapping("/person/login-form")
    public String personLoginForm() {
        return "person/login-form";
    }

    @PostMapping("/person/login")
    public String personLogin(UserRequest.LoginDTO requestDTO) {
        session.setAttribute("sessionUser", userService.로그인(requestDTO));
        return "redirect:/person/main";
    }


    //기업 개인 로그아웃
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    //회사 정보 및 수정
    //회사 정보 수정
    @GetMapping("/company/info")
    public String companyInfo(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원조회(sessionUser.getId());
        request.setAttribute("user", user);
        return "company/info";
    }

    @GetMapping("/company/info/update-form")
    public String companyInfoUpdateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원조회(sessionUser.getId());
        request.setAttribute("user", user);
        return "company/info-update-form";
    }

    @PostMapping("/company/info/update")
    public String companyInfoUpdate(UserRequest.CompanyInfoUpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newsessionUser = userService.회원수정(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newsessionUser);
        return "redirect:/company/info";
    }

    //개인 프로필 정보 및 수정
    @GetMapping("/person/info")
    public String personal() {
        return "person/info";
    }

    @GetMapping("/person/info/update-form")
    public String personInfoInfoUpdateForm() {
        return "person/info-update-form";
    }

    @PostMapping("/person/info/update")
    public String personInfoUpdate() {
        return "redirect:/person/info";
    }
}