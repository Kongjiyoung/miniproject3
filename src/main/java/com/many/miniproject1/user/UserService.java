package com.many.miniproject1.user;

import com.many.miniproject1._core.errors.exception.Exception401;
import com.many.miniproject1._core.errors.exception.Exception404;
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


    public User findByUser(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return user;
    }

    public User findByPersonId(int id) {
        return userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
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
    public UserResponse.CompanyDTO updatePersonInfo(Integer personId, UserRequest.PersonInfoUpdateDTO reqDTO) {
        User user = userJPARepository.findById(personId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));

        String encodedImageData = reqDTO.getProfile();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedImageData);
        String profilename= UUID.nameUUIDFromBytes(decodedBytes).randomUUID()+"_" + reqDTO.getProfileName();
        try {
            Path path = Path.of("./images/" + profilename);
            Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setProfile(profilename);
        user.setName(reqDTO.getName());
        user.setBirth(reqDTO.getBirth());
        user.setAddress(reqDTO.getAddress());
        user.setTel(reqDTO.getTel());
        user.setEmail(reqDTO.getEmail());
        user = userJPARepository.save(user);
        return new UserResponse.CompanyDTO(user);
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

        String encodedImageData = reqDTO.getProfile();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedImageData);
        String profilename= UUID.nameUUIDFromBytes(decodedBytes).randomUUID()+"_" + reqDTO.getProfileName();
        try {
            Path path = Path.of("./images/" + profilename);
            Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 비밀번호 업데이트
        if (StringUtils.isNotEmpty(reqDTO.getNewPassword())) {
            user.setPassword(reqDTO.getNewPassword());
        }


        if (reqDTO.getProfile()!=null){
            user.setProfile(profilename);
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


        return userJPARepository.save(user);
    }

    public SessionUser login(UserRequest.LoginDTO reqDTO) {

        User user  = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다"));
        return new SessionUser(user);
    }

    @Transactional
    public User compJoin(UserRequest.CompanyJoinDTO reqDTO) {

        User user = reqDTO.toEntity();
        user.setRole("company");
        userJPARepository.save(user);
        return user;
    }

    @Transactional
    public UserResponse.CompanyDTO companyJoin(UserRequest.CompanyJoinDTO reqDTO) {
        User user = userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.CompanyDTO(user);
    }
}
