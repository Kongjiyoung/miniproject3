package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageService;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1._core.errors.exception.Exception401;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserJPARepository userJPARepository;


    public User findByUser(int id){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }


    @Transactional
    public User personUpdate(int id,UserRequest.PersonUpdateDTO reqDTO){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        MultipartFile profile = reqDTO.getProfile();
        String profileFilename = UUID.randomUUID() + "_" + profile.getOriginalFilename();
        Path profilePath = Paths.get("./images/" + profileFilename);
        try {
            Files.write(profilePath, profile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user.setProfile(profileFilename);
        user.setAddress(reqDTO.getAddress());
        user.setEmail(reqDTO.getEmail());
        user.setTel(reqDTO.getTel());
        user.setBirth(reqDTO.getBirth());
        user.setName(reqDTO.getName());
        user.setPassword(reqDTO.getPassword());
        return userJPARepository.save(user);
    }

    @Transactional
    public void personJoin(UserRequest.PersonJoinDTO reqDTO) {
        // 이미지 저장
        String profileFilename = ProfileImageService.saveProfile(reqDTO.getProfile());

        // 사용자 정보 저장
        User user = reqDTO.toEntity();
        user.setProfile(profileFilename);
        userJPARepository.save(user);
    }

    @Transactional
    public User CompanyInfoUpdate(int id, UserRequest.CompanyInfoUpdateDTO requestDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        MultipartFile profile = requestDTO.getProfile();
        String profileFilename = UUID.randomUUID() + "_" + profile.getOriginalFilename();
        Path profilePath = Paths.get("./images/" + profileFilename);
        try {
            Files.write(profilePath, profile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 비밀번호 업데이트
        if (StringUtils.isNotEmpty(requestDTO.getNewPassword())) {
            user.setPassword(requestDTO.getNewPassword());
        }

        user.setProfile(profileFilename);
        user.setAddress(requestDTO.getAddress());
        user.setTel(requestDTO.getTel());
        user.setEmail(requestDTO.getEmail());

        return userJPARepository.save(user);
    }

    public User Login(UserRequest.LoginDTO reqestDTO) {

        User sessionUser = userJPARepository.findByUsernameAndPassword(reqestDTO.getUsername(), reqestDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

    @Transactional
    public void 기업가입(UserRequest.CompanyJoinDTO requestDTO){
        String profileFilename = ProfileImageService.saveProfile(requestDTO.getProfile());

        User user = requestDTO.toEntity();
        user.setRole("company");
        user.setProfile(profileFilename);
        userJPARepository.save(user);
    }
}
