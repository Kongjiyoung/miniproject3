package com.many.miniproject1.user;

import com.many.miniproject1._core.utils.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService userService;


    @PostMapping("/company/join")
    public ResponseEntity<?> companyJoin(@RequestBody UserRequest.CompanyJoinDTO requestDTO) {
        UserResponse.CompanyDTO respDTO = userService.companyJoin(requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/company/login")

    public ResponseEntity<?> companyLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }


    @PostMapping("/person/join")
    public ResponseEntity<?> personJoin(@RequestBody UserRequest.PersonJoinDTO reqDTO) {

        UserResponse.PersonDTO respDTO = userService.personJoin(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/person/login")
    public ResponseEntity<?> personLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
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
    @GetMapping("/companies/{id}/info")
    public ResponseEntity<?> companyInfo(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.CompanyDTO respDTO = userService.findByCompany(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/company/info")
    public ResponseEntity<?> companyInfoUpdate(@RequestBody UserRequest.CompanyInfoUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.companyInfoUpdate(14, reqDTO);
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

    @PutMapping("/people/{id}/info")
    public ResponseEntity<?> personInfoUpdate(@PathVariable Integer id, @RequestBody UserRequest.PersonInfoUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findByPersonId(1);
        newSessionUser.update(id, reqDTO);
        session.setAttribute("sessionUser", newSessionUser);
        UserResponse.PersonDTO respDTO = userService.updatePersonInfo(id, reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}