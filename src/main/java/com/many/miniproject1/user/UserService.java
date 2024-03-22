package com.many.miniproject1.user;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserJPARepository userJPARepository;

    @Transactional
    public User 회원수정(int id, UserRequest.CompanyInfoUpdateDTO requestDTO){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        if (!StringUtils.isEmpty(requestDTO.getProfileBase64())) {
            // Base64로 인코딩된 이미지 문자열을 디코딩하여 바이트 배열로 변환
            byte[] profileImageBytes = Base64.getDecoder().decode(requestDTO.getProfileBase64());
            String profileImageBase64 = Base64.getEncoder().encodeToString(profileImageBytes); // 다시 Base64로 인코딩 (원하는 경우)
            user.setProfile(profileImageBase64); // 이미지를 Base64로 인코딩한 문자열로 저장
        }

        user.setAddress(requestDTO.getAddress());
        user.setTel(requestDTO.getTel());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        return user;
    }

    public User 회원조회(int id){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }

    public User 로그인(UserRequest.LoginDTO reqestDTO){
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqestDTO.getUsername(), reqestDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

}
