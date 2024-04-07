package com.many.miniproject1.user;

import com.many.miniproject1._core.common.ProfileImageSaveUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserRequest {

    @NoArgsConstructor
    @Data
    public static class PersonJoinDTO {
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "아이디는 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String username;
        @NotEmpty(message = "이름은 공백일 수 없습니다")
        @Size(min = 2, max = 10)
        private String name;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty(message = "생일은 공백일 수 없습니다")
        private String birth;
        @NotEmpty
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty(message = "주소 공백일 수 없습니다")
        @NotEmpty
        private String address;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        public User toEntity() {
            return User.builder()
                    .role("person")
                    .profile(ProfileImageSaveUtil.convertToBase64(profile, profileName))
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

    @NoArgsConstructor
    @Data
    public static class CompanyJoinDTO {
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;     // 사진
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "회사이름은 공백일 수 없습니다")
        @Size(min = 1, max = 20)
        private String companyName; // 회사명
        @NotEmpty(message = "사업자번호는 공백일 수 없습니다")
        @Pattern(regexp = "^?([0-9]{3})-?([0-9]{2})-?([0-9]{5})$", message = "000-00-00000식으로 작성해주세요")
        private String companyNum;  // 사업자번호
        @NotEmpty(message = "아이디는 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String username;    // 로그인ID
        @NotEmpty(message = "이름은 공백일 수 없습니다")
        @Size(min = 2, max = 10)
        private String name;        // 담당자 이름
        @NotEmpty(message = "전화번호는 공백일 수 없습니다")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;         // 전화번호
        @NotEmpty(message = "주소 공백일 수 없습니다")
        private String address;     // 회사 주소
        @NotEmpty(message = "이메일은 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;       // 담당자 이메일
        @NotEmpty(message = "비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String password;    // 비밀번호

        public User toEntity() {
            return User.builder()
                    .role("company")
                    .profile(ProfileImageSaveUtil.convertToBase64(profile, profileName))
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

    @NoArgsConstructor
    @Data
    public static class PersonInfoUpdateDTO {
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "이름은 공백일 수 없습니다")
        private String name;
        @NotEmpty(message = "생일은 공백일 수 없습니다")
        private String birth;
        @NotEmpty(message = "번호는 공백일 수 없습니다")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty(message = "주소는 공백일 수 없습니다")
        private String address;
        @NotEmpty(message = "이메일은 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty(message = "비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String password;
        @NotEmpty(message = "새로운 비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String newPassword;
    }

    @NoArgsConstructor
    @Data
    public static class CompanyInfoUpdateDTO {
        @NotEmpty(message = "사진은 공백일 수 없습니다")
        private String profile;
        @NotEmpty(message = "사진이름은 공백일 수 없습니다")
        private String profileName;
        @NotEmpty(message = "주소는 공백일 수 없습니다")
        private String address;
        @NotEmpty(message = "전화번호는 공백일 수 없습니다")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "000-0000-0000식으로 작성해주세요")
        private String tel;
        @NotEmpty(message = "이메일은 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        @NotEmpty(message = "비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String password;
        @NotEmpty(message = "새로운비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String newPassword;
    }


    @NoArgsConstructor
    @Data
    public static class LoginDTO {
        @NotEmpty(message = "아이디는 공백일 수 없습니다")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String username;
        @NotEmpty(message = "비밀번호는 공백일 수 없습니다")
        @Size(min = 4, max = 20)
        private String password;
    }
}