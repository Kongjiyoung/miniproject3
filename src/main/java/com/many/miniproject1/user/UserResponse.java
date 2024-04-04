package com.many.miniproject1.user;

import lombok.Data;

public class UserResponse {
    @Data
    public static class PersonDTO {
        private Integer id;
        private String role;
        private String profile;
        private String username;
        private String name;
        private String email;
        private String birth;
        private String tel;
        private String address;
        private String password;

        public PersonDTO(User user) {
            this.id = user.getId();
            this.role = user.getRole();
            this.profile = user.getProfile();
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.birth = String.valueOf(user.getBirth());
            this.tel = user.getTel();
            this.address = user.getAddress();
            this.password = user.getPassword();
        }
    }

    @Data
    public static class CompanyDTO {
        private Integer id;
        private String role;        // 구직자 or 회사
        private String profile;     // 사진
        private String companyName; // 회사명
        private String companyNum;  // 사업자번호
        private String username;    // 로그인ID
        private String name;        // 담당자 이름
        private String tel;         // 전화번호
        private String address;     // 회사 주소
        private String email;       // 담당자 이메일
        private String password;    // 비밀번호

        public CompanyDTO(User user) {
            this.id = user.getId();
            this.role = user.getRole();
            this.profile = user.getProfile();
            this.companyName = user.getCompanyName();
            this.companyNum = user.getCompanyNum();
            this.username = user.getUsername();
            this.name = user.getName();
            this.tel = user.getTel();
            this.address = user.getAddress();
            this.email = user.getEmail();
            this.password = user.getPassword();
        }
    }
}
