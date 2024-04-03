package com.many.miniproject1.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
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
    public static class PersonJoinDTO {
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        private String username;
        @NotEmpty
        private String name;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty
        private String birth;
        @NotEmpty
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty
        private String address;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        public User toEntity() {
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            return User.builder()
                    .role("person")
                    .profile(profilename)
                    .profileName(profileName)
                    .username(username)
                    .name(name)
                    .email(email)
                    .birth(birth)
                    .tel(tel)
                    .address(address)
                    .password(password)
                    .build();
        }
    }

    @Data
    public static class CompanyInfoUpdateDTO {
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        private String address;
        @NotEmpty
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String newPassword;

        public CompanyInfoUpdateDTO() {
        }

        @Builder
        public CompanyInfoUpdateDTO(User user) {
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.profile = user.getProfile();
            this.profileName = user.getProfileName();
            this.address = user.getAddress();
            this.tel = user.getTel();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.newPassword = user.getPassword();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class CompanyJoinDTO {
        @NotEmpty
        private String profile;     // 사진
        @NotEmpty
        private String profileName;
        @NotEmpty
        @Size(min = 1, max = 20)
        private String companyName; // 회사명
        @NotEmpty
        @Pattern(regexp = "^?([0-9]{3})-?([0-9]{2})-?([0-9]{5})$", message = "000-00-00000식으로 작성해주세요")
        private String companyNum;  // 사업자번호
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String username;    // 로그인ID
        @NotEmpty
        private String name;        // 담당자 이름
        @NotEmpty
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;         // 전화번호
        @NotEmpty
        private String address;     // 회사 주소
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;       // 담당자 이메일
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;    // 비밀번호

        public User toEntity() {
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            return User.builder()
                    .role("company")
                    .profile(profilename)
                    .profileName(profileName)
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
        @NotEmpty
        private String profile;
        @NotEmpty
        private String profileName;
        @NotEmpty
        private String name;
        @NotEmpty
        private String birth;
        @NotEmpty
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty
        private String address;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String newPassword;

        public PersonInfoUpdateDTO() {
        }

        @Builder
        public PersonInfoUpdateDTO(User user) {
            byte[] decodedBytes = Base64.getDecoder().decode(profile);
            String profilename = UUID.nameUUIDFromBytes(decodedBytes).randomUUID() + "_" + profileName;
            try {
                Path path = Path.of("./images/" + profilename);
                Files.write(path, decodedBytes); // 바이트 배열을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.profile = user.getProfile();
            this.profileName = user.getProfileName();
            this.name = user.getName();
            this.birth = user.getBirth();
            this.tel = user.getTel();
            this.address = user.getAddress();
            this.email = user.getEmail();
        }
    }
}