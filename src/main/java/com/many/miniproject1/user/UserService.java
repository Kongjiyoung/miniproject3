package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception404;
import com.many.miniproject1._core.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
    }

    public UserResponse.PersonDTO findByPerson(Integer id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        return new UserResponse.PersonDTO(user);
    }

    public UserResponse.CompanyDTO findByCompany(Integer id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        return new UserResponse.CompanyDTO(user);
    }

    @Transactional
    public UserResponse.PersonDTO updatePersonInfo(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(personId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        user.updatePersonInfo(reqDTO);
        user.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(),reqDTO.getProfileName()));
        user.setPassword(reqDTO.getNewPassword());

        return new UserResponse.PersonDTO(user);
    }

    @Transactional
    public UserResponse.CompanyDTO companyInfoUpdate(Integer id, UserRequest.CompanyInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        user.updateCompanyInfo(reqDTO);
        user.setProfile(ProfileImageSaveUtil.convertToBase64(reqDTO.getProfile(),reqDTO.getProfileName()));
        user.setPassword(reqDTO.getNewPassword());

        return new UserResponse.CompanyDTO(user);
    }

    public String  login(UserRequest.LoginDTO reqDTO) {
        User user = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception404("아이디와 패스워드를 확인해 주세요"));

        return JwtUtil.create(user);
    }
}
