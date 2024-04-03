package com.many.miniproject1.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SessionUser {
    private Integer id;
    private String role;
    private String username;
//    private String email;
//    private String password;
//    private String name;
//    private String tel;
//    private String companyName;
//    private String address;
//    private String companyNum;
//    private String profile;
//    private String profileName;
//    private String birth;
    private Timestamp createdAt;


    public SessionUser(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
    @Builder
//    public SessionUser(Integer id, String role, String username, String email, String password, String name, String tel, String companyName, String address, String companyNum, String profile, String profileName, String birth, Timestamp createdAt) {
    public SessionUser(Integer id, String role, String username, Timestamp createdAt) {
        this.id = id;
        this.role = role;
        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.tel = tel;
//        this.companyName = companyName;
//        this.address = address;
//        this.companyNum = companyNum;
//        this.profile = profile;
//        this.profileName = profileName;
//        this.birth = birth;
        this.createdAt = createdAt;
    }
}
