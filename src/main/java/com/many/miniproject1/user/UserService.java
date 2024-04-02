package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserJPARepository userJPARepository;


    public User findByUser(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }

    public User findByPersonId(int id) {
        return userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
    }

    //    @Transactional
//    public User personUpdate(int id,UserRequest.PersonUpdateDTO reqDTO){
//        User user = userJPARepository.findById(id)
//                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
//
//        String profileFilename = ProfileImageSaveUtil.save(reqDTO.getProfile());
//
//        user.setProfile(profileFilename);
//        user.setAddress(reqDTO.getAddress());
//        user.setEmail(reqDTO.getEmail());
//        user.setTel(reqDTO.getTel());
//        user.setBirth(reqDTO.getBirth());
//        user.setName(reqDTO.getName());
//        user.setPassword(reqDTO.getPassword());
//        return userJPARepository.save(user);
//    }
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
    public User updatePersonInfo(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(personId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        //  String profileFilename = ProfileImageSaveUtil.save(reqDTO.getProfile());

        // user.update(personId, reqDTO, profileFilename);
        user.update(personId, reqDTO);
//        user.setProfile(profileFilename);
//        user.setName(reqDTO.getName());
//        user.setBirth(Date.valueOf(reqDTO.getBirth().toLocalDate()));
//        user.setAddress(reqDTO.getAddress());
//        user.setTel(reqDTO.getTel());
//        user.setEmail(reqDTO.getEmail());
        // user = userJPARepository.save(user);
        return user;
    }

    @Transactional
    public UserResponse.PersonDTO personJoin(UserRequest.PersonJoinDTO reqDTO) {
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.PersonDTO(user);
    }

    @Transactional
    public User companyInfoUpdate(int id, UserRequest.CompanyInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        String profileFilename = ProfileImageSaveUtil.save(reqDTO.getProfile());

        // 비밀번호 업데이트
        if (StringUtils.isNotEmpty(reqDTO.getNewPassword())) {
            user.setPassword(reqDTO.getNewPassword());
        }

        user.setProfile(profileFilename);
        user.setAddress(reqDTO.getAddress());
        user.setTel(reqDTO.getTel());
        user.setEmail(reqDTO.getEmail());

        return userJPARepository.save(user);
    }

    public User login(UserRequest.LoginDTO reqDTO) {

        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return sessionUser;
    }

    @Transactional
    public User compJoin(UserRequest.CompanyJoinDTO reqDTO) {

        User user = reqDTO.toEntity();
        user.setRole("company");
        userJPARepository.save(user);
        return user;
    }

    public UserResponse.CompanyDTO companyJoin(UserRequest.CompanyJoinDTO reqDTO) {
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.CompanyDTO(user);
    }
}
