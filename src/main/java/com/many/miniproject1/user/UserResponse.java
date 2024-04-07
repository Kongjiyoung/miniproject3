package com.many.miniproject1.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class PersonDTO {
        private Integer id;
        private String role;
        private String profile;
        private String profileName;
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
            this.profileName = user.getProfileName();
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
        private String role;
        private String profile;
        private String profileName;
        private String companyName;
        private String companyNum;
        private String username;
        private String name;
        private String tel;
        private String address;
        private String email;
        private String password;

        public CompanyDTO(User user) {
            this.id = user.getId();
            this.role = user.getRole();
            this.profile = user.getProfile();
            this.profileName = user.getProfileName();
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
