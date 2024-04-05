package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1._core.utils.JwtUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserJPARepository userJPARepository;

    @Transactional
    public UserResponse.PersonDTO personJoin(UserRequest.PersonJoinDTO reqDTO) {
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.PersonDTO(user);
    }

    @Transactional
    public UserResponse.CompanyDTO companyJoin(UserRequest.CompanyJoinDTO reqDTO) {
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.CompanyDTO(user);
    }
    public User findByUser(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }

    public UserResponse.PersonDTO findByPerson(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return new UserResponse.PersonDTO(user);
    }

    public UserResponse.CompanyDTO findByCompany(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return new UserResponse.CompanyDTO(user);
    }
    @Transactional
    public UserResponse.PersonDTO updatePersonInfo(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(personId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        // 비밀번호 업데이트
        if (StringUtils.isNotEmpty(reqDTO.getNewPassword())) {
            user.setPassword(reqDTO.getNewPassword());
        }

        if (reqDTO.getProfile()!=null){
            user.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(), reqDTO.getProfileName()));
        }
        if (reqDTO.getName()!=null){
            user.setName(reqDTO.getName());
        }
        if (reqDTO.getBirth()!=null){
            user.setBirth(reqDTO.getBirth());
        }
        if (reqDTO.getAddress()!=null){
            user.setAddress(reqDTO.getAddress());
        }
        if (reqDTO.getTel()!=null){
            user.setTel(reqDTO.getTel());
        }
        if (reqDTO.getEmail()!=null){
            user.setEmail(reqDTO.getEmail());
        }


        return new UserResponse.PersonDTO(user);
    }
    @Transactional
    public UserResponse.CompanyDTO companyInfoUpdate(int id, UserRequest.CompanyInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        // 비밀번호 업데이트
        if (StringUtils.isNotEmpty(reqDTO.getNewPassword())) {
            user.setPassword(reqDTO.getNewPassword());
        }

        if (reqDTO.getProfile()!=null){
            user.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(), reqDTO.getProfileName()));
        }
        if (reqDTO.getAddress()!=null){
            user.setAddress(reqDTO.getAddress());
        }
        if (reqDTO.getTel()!=null){
            user.setTel(reqDTO.getTel());
        }
        if (reqDTO.getEmail()!=null){
            user.setEmail(reqDTO.getEmail());
        }

        return new UserResponse.CompanyDTO(user);
    }



    public String  login(UserRequest.LoginDTO reqDTO) {
        User user = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        String jwt = JwtUtil.create(user);
        return jwt;
    }

}
