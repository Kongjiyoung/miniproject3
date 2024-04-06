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
    public ResponseEntity<?> companyJoin(@Valid @RequestBody UserRequest.CompanyJoinDTO requestDTO, Errors errors) {
        UserResponse.CompanyDTO respDTO = userService.companyJoin(requestDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PostMapping("/company/login")
    public ResponseEntity<?> companyLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil(null));
    }


    @PostMapping("/person/join")
    public ResponseEntity<?> personJoin(@Valid @RequestBody UserRequest.PersonJoinDTO reqDTO, Errors errors) {
        UserResponse.PersonDTO respDTO = userService.personJoin(reqDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }


    @PostMapping("/person/login")
    public ResponseEntity<?> personLogin(@RequestBody UserRequest.LoginDTO reqDTO) {
        String jwt = userService.login(reqDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + jwt).body(new ApiUtil(null));
    }

    //기업 개인 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    //회사 정보 및 수정
    //회사 정보 수정
    @GetMapping("/api/company/my-page/info") // 기업 정보를 보여주는데 비밀번호도 넘겨야 하나?
    public ResponseEntity<?> companyInfo() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CompanyDTO respBody = userService.findByCompany(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respBody));
    }

    @PutMapping("/api/company/my-page/info")
    public ResponseEntity<?> companyInfoUpdate(@Valid @RequestBody UserRequest.CompanyInfoUpdateDTO reqDTO, Errors errors) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.CompanyDTO respDTO = userService.companyInfoUpdate(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", respDTO);

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    @PutMapping("/companies/{id}/info")
//    public ResponseEntity<?> companyInfoUpdate(@PathVariable Integer id, @Valid @RequestBody UserRequest.CompanyInfoUpdateDTO
//            reqDTO, Errors errors) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        User newSessionUser = userService.companyInfoUpdate(id, reqDTO);
//        session.setAttribute("sessionUser", newSessionUser);
//        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
//    }

    //개인 프로필 정보 및 수정
    @GetMapping("/api/person/my-page/info")
    public ResponseEntity<?> personInfo() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        UserResponse.PersonDTO respDTO = userService.findByPerson(sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    @PutMapping("/api/person/my-page/info")
    public ResponseEntity<?> personInfoUpdate(@RequestBody UserRequest.PersonInfoUpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
//        SessionUser newSessionUser = userService.updatePersonInfo(sessionUser.getId(), reqDTO);
        UserResponse.PersonDTO newSessionUser = userService.updatePersonInfo(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }
}