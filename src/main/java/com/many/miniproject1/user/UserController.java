package com.many.miniproject1.user;

import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService userService;


    @PostMapping("/company/join")
    public ResponseEntity<?> companyJoin(@RequestBody UserRequest.CompanyJoinDTO requestDTO) {
        UserResponse.CompanyDTO respDTO=userService.companyJoin(requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/company/login")
    public ResponseEntity<?> companyLogin(UserRequest.LoginDTO requestDTO) {
        session.setAttribute("sessionUser", userService.login(requestDTO));
        User user=userService.login(requestDTO);
        return ResponseEntity.ok(new ApiUtil<>(user));
    }


    @PostMapping("/person/join")
    public ResponseEntity<?> personJoin(UserRequest.PersonJoinDTO reqDTO) {
        UserResponse.PersonDTO respDTO=userService.personJoin(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/person/login")
    public ResponseEntity<?> personLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser=userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(null));
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
        UserResponse.CompanyDTO respDTO = userService.findByCompany(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/company/info/update-form")
    public ResponseEntity<?> companyInfoUpdateForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PutMapping("/company/info/update")
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
        UserResponse.PersonDTO respDTO = userService.findByPerson(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @GetMapping("/person/info/update-form")
    public ResponseEntity<?> personInfoInfoUpdateForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.findByUser(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PutMapping("/person/info/update")
    public ResponseEntity<?> personInfoUpdate(UserRequest.PersonUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.personUpdate(sessionUser.getId(), reqDTO);
        session.setAttribute("newSessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }
}