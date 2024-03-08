package com.many.miniproject1.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final Environment env;
    private final UserFileService userFileService;

    // 회사 회원가입
    @GetMapping("/company/joinForm")
    public String companyJoinForm() {
        return "company/joinForm";
    }

    @PostMapping("/company/join")
    public String companyJoin(UserRequest.JoinDTO requestDTO) {
        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            // 이미지 파일 처리
            try {
                String absolutePath = userFileService.saveFile(profileImage);  // 파일 저장 및 절대 경로 반환
                String filename = Paths.get(absolutePath).getFileName().toString();  // 파일 이름 추출
                profilePath = "/images/" + filename;  // 웹 서버의 상대 경로 생성
//                profilePath = userFileService.saveFile(profileImage);  // 파일 저장 및 상대 경로 반환

                // 실제로 저장된 파일의 이름 출력
                System.out.println("Saved file path: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        requestDTO.setProfilePath(profilePath); // 프로필 이미지 경로를 DTO에 설정
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
        if (user == null) {
            return "/company/loginForm";
        } else if (!user.getRole().equals("company")) {
            return "error/404";
        } else { // 조회 됐음 (인증됨)
            Boolean isCompany;
            isCompany = true;
            session.setAttribute("sessionUser", user);
            session.setAttribute("isCompany", isCompany);
        }

        return "redirect:/company/main";
    }


    // 개인 로그인 회원가입
    @GetMapping("/person/joinForm")
    public String personJoinForm() {
        return "person/joinForm";
    }

    @PostMapping("/person/join")
    public String personJoin(UserRequest.JoinDTO requestDTO) {
        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            // 이미지 파일 처리
            try {
                String absolutePath = userFileService.saveFile(profileImage);  // 파일 저장 및 절대 경로 반환
                String filename = Paths.get(absolutePath).getFileName().toString();  // 파일 이름 추출
                profilePath = "/images/" + filename;  // 웹 서버의 상대 경로 생성
//                profilePath = userFileService.saveFile(profileImage);  // 파일 저장 및 상대 경로 반환

                // 실제로 저장된 파일의 이름 출력
                System.out.println("Saved file path: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        requestDTO.setProfilePath(profilePath); // 프로필 이미지 경로를 DTO에 설정
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

        if (user == null) {
            return "/person/loginForm";
        } else if (!user.getRole().equals("person")) {
            return "error/404";
        } else { // 조회 됐음 (인증됨)
            Boolean isPerson;
            isPerson = true;
            session.setAttribute("sessionUser", user);
            session.setAttribute("isPerson", isPerson);
        }
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
        if (sessionUser == null) {
            return "/company/loginForm";
        }
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "company/companyInfo";
    }
//    @GetMapping("/images/{filename}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename)throws FileNotFoundException {
//        Resource file = userFileService.loadFile(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }

    @GetMapping("/company/info/updateForm")
    public String companyInfoUpdateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // sessionUser가 null인 경우, 로그인 페이지로 리다이렉트
            return "redirect:/company/loginForm";
        }
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "company/updateInfoForm";
    }

    @PostMapping("/company/info/update")
    public String companyInfoUpdate(UserRequest.CompanyUpdateDTO requestDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            // 이미지 파일 처리

            try {
//                profilePath = userFileService.saveFile(profileImage);  // 파일 저장 및 상대 경로 반환
                String absolutePath = userFileService.saveFile(profileImage);  // 파일 저장 및 절대 경로 반환
                String filename = Paths.get(absolutePath).getFileName().toString();  // 파일 이름 추출
                profilePath = "/images/" + filename;  // 웹 서버의 상대 경로 생성
                // 실제로 저장된 파일의 이름 출력
                System.out.println("Saved file path: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User user = userRepository.findById(sessionUser.getId());
//        session.setAttribute("sessionUser", user);
        request.setAttribute("user", user);

        requestDTO.setProfilePath(profilePath); // 프로필 이미지 경로를 DTO에 설정
        userRepository.companyUpdate(requestDTO, sessionUser.getId(), requestDTO.getNewPassword());

        System.out.println(requestDTO);
        return "redirect:/company/info";
    }

    //개인 프로필 정보 및 수정
    @GetMapping("/person/info")
    public String personal(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // sessionUser가 null인 경우, 로그인 페이지로 리다이렉트
            return "/person/loginForm";
        }
        User user = userRepository.findById(sessionUser.getId());

        request.setAttribute("user", user);

        return "person/personalInfo";
    }

    @GetMapping("/person/info/updateForm")
    public String personInfoInfoUpdateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // sessionUser가 null인 경우, 로그인 페이지로 리다이렉트
            return "person/loginForm";
        }
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "person/updatePersonalForm";
    }

    @PostMapping("/person/info/update")
    public String personInfoUpdate(UserRequest.PersonUpdateDTO requestDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        MultipartFile profileImage = requestDTO.getProfile();
        String profilePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            // 이미지 파일 처리

            try {
//                profilePath = userFileService.saveFile(profileImage);  // 파일 저장 및 상대 경로 반환
                String absolutePath = userFileService.saveFile(profileImage);  // 파일 저장 및 절대 경로 반환
                String filename = Paths.get(absolutePath).getFileName().toString();  // 파일 이름 추출
                profilePath = "/images/" + filename;  // 웹 서버의 상대 경로 생성
                // 실제로 저장된 파일의 이름 출력
                System.out.println("Saved file path: " + profilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User user = userRepository.findById(sessionUser.getId());
//        session.setAttribute("sessionUser", user);
        request.setAttribute("user", user);

        requestDTO.setProfilePath(profilePath); // 프로필 이미지 경로를 DTO에 설정
        userRepository.personUpdate(requestDTO, sessionUser.getId(), requestDTO.getNewPassword());

        System.out.println(requestDTO);
        return "redirect:/person/info";
    }


}