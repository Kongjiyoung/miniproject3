package com.many.miniproject1.user;

import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService userService;


    @PostMapping("/company")
    public ResponseEntity<?> companyJoin(UserRequest.CompanyJoinDTO requestDTO) {
        User user=userService.compJoin(requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PostMapping("/company/login")
    public ResponseEntity<?> companyLogin(UserRequest.LoginDTO requestDTO) {
        session.setAttribute("sessionUser", userService.login(requestDTO));
        User user=userService.login(requestDTO);
        return ResponseEntity.ok(new ApiUtil<>(user));
    }


    @PostMapping("/person/join")
    public ResponseEntity<?> personJoin(UserRequest.PersonJoinDTO reqDTO) {
        User user=userService.personJoin(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PostMapping("/person/login")
    public ResponseEntity<?> personLogin(UserRequest.LoginDTO requestDTO) {
        session.setAttribute("sessionUser", userService.login(requestDTO));
        User user=userService.login(requestDTO);
        return ResponseEntity.ok(new ApiUtil<>(user));
    }


    //기업 개인 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil<>(null));
    }


    //회사 정보 및 수정
    //회사 정보 수정
    @GetMapping("/company/info")
    public ResponseEntity<?> companyInfo() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @GetMapping("/company/info/update-form")
    public ResponseEntity<?> companyInfoUpdateForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PostMapping("/company/info/update")
    public ResponseEntity<?> companyInfoUpdate(UserRequest.CompanyInfoUpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.companyInfoUpdate(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    //개인 프로필 정보 및 수정
    @GetMapping("/person/info")
    public ResponseEntity<?> personInfo() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @GetMapping("/person/info/update-form")
    public ResponseEntity<?> personInfoInfoUpdateForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PostMapping("/person/info/update")
    public ResponseEntity<?> personInfoUpdate(UserRequest.PersonUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.personUpdate(sessionUser.getId(), reqDTO);
        session.setAttribute("newSessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }
}