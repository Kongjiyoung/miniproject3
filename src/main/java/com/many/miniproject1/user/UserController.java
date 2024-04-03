package com.many.miniproject1.user;

import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService userService;


    @PostMapping("/company/join")
    public ResponseEntity<?> companyJoin(@RequestBody UserRequest.CompanyJoinDTO requestDTO) {
    public ResponseEntity<?> companyJoin(@Valid @RequestBody UserRequest.CompanyJoinDTO requestDTO, Errors errors) {
        UserResponse.CompanyDTO respDTO = userService.companyJoin(requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/company/login")
    public ResponseEntity<?> companyLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil(null));
        return ResponseEntity.ok().header("Authorization", "Bearer "+jwt).body(new ApiUtil(null));
    }


    @PostMapping("/person/join")
    public ResponseEntity<?> personJoin(@RequestBody UserRequest.PersonJoinDTO reqDTO) {
    public ResponseEntity<?> personJoin(@Valid @RequestBody UserRequest.PersonJoinDTO reqDTO, Errors errors) {

        UserResponse.PersonDTO respDTO = userService.personJoin(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/person/login")
    public ResponseEntity<?> personLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil(null));
        return ResponseEntity.ok().header("Authorization", "Bearer "+jwt).body(new ApiUtil(null));
    }


    //기업 개인 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    //회사 정보 및 수정
    //회사 정보 수정
    @GetMapping("/api/company/info")
    public ResponseEntity<?> companyInfo() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CompanyDTO respBody = userService.findByCompany(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respBody));
    }

    @PutMapping("/api/companies/{id}/info")
    public ResponseEntity<?> companyInfoUpdate(@RequestBody UserRequest.CompanyInfoUpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.companyInfoUpdate(sessionUser.getId(), reqDTO);
    @GetMapping("/companies/{id}/info")
    public ResponseEntity<?> companyInfo(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.CompanyDTO respDTO = userService.findByCompany(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/company/info")
    public ResponseEntity<?> companyInfoUpdate(@Valid @RequestBody UserRequest.CompanyInfoUpdateDTO reqDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.companyInfoUpdate(14, reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    //개인 프로필 정보 및 수정
    @GetMapping("/api/person/info")
    public ResponseEntity<?> personInfo() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.PersonDTO respDTO = userService.findByPerson(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/people/{id}/info")
    public ResponseEntity<?> personInfoUpdate(@RequestBody UserRequest.PersonInfoUpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        SessionUser newSessionUser = userService.updatePersonInfo(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    @PutMapping("/people/info")
    public ResponseEntity<?> personInfoUpdate(@Valid @RequestBody UserRequest.PersonInfoUpdateDTO reqDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.PersonDTO respDTO = userService.updatePersonInfo(sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}