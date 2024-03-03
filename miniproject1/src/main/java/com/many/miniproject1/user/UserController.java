package com.many.miniproject1.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    // 회사 회원가입
    @GetMapping("/company/joinForm")
    public String companyJoinForm() {
        return "company/joinForm";
    }

    @PostMapping("/company/join")
    public String companyJoin(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);
        userRepository.companySave(requestDTO);
        return "redirect:/company/loginForm";
    }

    // 회사 로그인
    @GetMapping("/company/loginForm")
    public String companyLoginForm() {
        return "company/loginForm";
    }

    @PostMapping("/company/login")
    public String companyLogin(UserRequest.LoginDTO requestDTO) {
        System.out.println(requestDTO);
//        if (requestDTO.getEmail().length() < 3) {
//            return "error/400";
//        }

        User user = userRepository.findByEmailAndPassword(requestDTO);

//        if (user == null) {
//            return "error/401";
//        } else { // 조회 됐음 (인증됨)
//            session.setAttribute("sessionUser", user);
//        }
        return "redirect:/";
    }


    // 개인 로그인 회원가입
    @GetMapping("/person/joinForm")
    public String personJoinForm() {
        return "person/joinForm";
    }

    @PostMapping("/person/join")
    public String personJoin(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);
        userRepository.personSave(requestDTO);
        return "redirect:/person/loginForm";
    }

    // 개인 로그인 회원가입
    @GetMapping("/person/loginForm")
    public String personLoginForm() {
        return "person/loginForm";
    }

    @PostMapping("/person/login")
    public String personLogin(UserRequest.LoginDTO requestDTO) {
        System.out.println(requestDTO);
//        if (requestDTO.getEmail().length() < 3) {
//            return "error/400";
//        }

        User user = userRepository.findByEmailAndPassword(requestDTO);

//        if (user == null) {
//            return "error/401";
//        } else { // 조회 됐음 (인증됨)
//            session.setAttribute("sessionUser", user);
//        }
        return "redirect:/";
    }



    //기업 개인 로그아웃
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }


    //회사 정보 및 수정
    //회사 정보 수정
    @GetMapping("/company/info")
    public String companyInfo() {
        return "company/companyInfo";
    }
    //    수정하기 버튼 누르면 수정하기 되게 해주세요. 하고 주석 지워주세요.

    // 수정완료/취소 버튼 누르면 자원을 찾을 수 없음이라 나옴. 그것 수정하고 주석 지워주세요.
    @GetMapping("/company/info/updateForm")
    public String companyInfoUpdateForm() {
        return "company/updateInfoForm";
    }

//   여기에도 머스치에도 post를 적었는데 get이 나오는 이유가 무엇일까요.
    @PostMapping("/company/info/update")
    public String companyInfoUpdate() {
        return "redirect:/company/companyInfo";
    }

    //개인 프로필 정보 및 수정
    @GetMapping("/person/info")
    public String personal() {
        return "person/personalInfo";
    }

    @GetMapping("/person/info/updateForm")
    public String personInfoInfoUpdateForm() {
        return "person/updatePersonalForm";
    }

    @PostMapping("/person/info/update")
    public String personInfoUpdate() {
        return "redirect:/person/Info";
    }


}