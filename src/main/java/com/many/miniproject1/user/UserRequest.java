package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.Base64;

import java.util.UUID;

public class UserRequest {

    @Data
    public static class PersonUpdateDTO {
        private String email;
        private String name;
        private String password;
        private String tel;
        private String address;
        private Date birth;
        private MultipartFile profile;
    }

    @Data
    public static class PersonJoinDTO {
        private String role;
        private MultipartFile profile;
        private String username;
        private String name;
        private String email;
        private String birth;
        private String tel;
        private String address;
        private String password;

        public User toEntity() {
            String profileImagePath = ProfileImageSaveUtil.save(profile);
            return User.builder()
                    .role("person")
                    .profile(profileImagePath)
                    .username(username)
                    .name(name)
                    .email(email)
                    .birth(Date.valueOf(birth))
                    .tel(tel)
                    .address(address)
                    .password(password)
                    .build();
        }
    }

    @Data
    public static class CompanyInfoUpdateDTO {
        private Integer id;
        private MultipartFile profile;
        private String address;
        private String tel;
        private String email;
        private String password;
        private String newPassword;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class CompanyJoinDTO {
        private String role;        // 구직자 or 회사
        private String profile;     // 사진
        private String profileName;
        private String companyName; // 회사명
        private String companyNum;  // 사업자번호
        private String username;    // 로그인ID
        private String name;        // 담당자 이름
        private String tel;         // 전화번호
        private String address;     // 회사 주소
        private String email;       // 담당자 이메일
        private String password;    // 비밀번호

        public User toEntity() {
            String encodedImageData = profile;
            byte[] decodedBytes = Base64.getDecoder().decode(encodedImageData);
            String profilename=UUID.nameUUIDFromBytes(decodedBytes).randomUUID()+"_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            return User.builder()
                    .role("company")
                    .profile(profilename)
                    .companyName(companyName)
                    .companyNum(companyNum)
                    .username(username)
                    .name(name)
                    .tel(tel)
                    .address(address)
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Data
    public static class PersonInfoUpdateDTO {
        private Integer id;
        //private MultipartFile profile;
        private String name;
        // private Date birth;
        private String tel;
        private String address;
        private String email;
        private String password;

//        @Builder
//        public PersonInfoUpdateDTO(User user) {
//            this.id = user.getId();
//            this.profile = user.getProfile();
//            this.name = user.getName();
//            this.birth = user.getName();
//            this.tel = user.getTel();
//            this.address = user.getAddress();
//            this.email = user.getEmail();
//        }
    }
}