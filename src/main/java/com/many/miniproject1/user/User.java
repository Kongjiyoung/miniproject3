package com.many.miniproject1.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "user_tb")
@Data
@Entity
public class User {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String name;
    private String tel;
    private String companyName;
    private String address;
    private String companyNum;
    private String profile;
    private String profileName;
    private String birth;
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String role, String username, String email, String password, String name, String tel, String companyName, String address, String companyNum, String profile, String profileName, String birth, Timestamp createdAt) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.companyName = companyName;
        this.address = address;
        this.companyNum = companyNum;
        this.profile = profile;
        this.profileName = profileName;
        this.birth = birth;
        this.createdAt = createdAt;
    }

    public void updatePersonInfo(UserRequest.PersonInfoUpdateDTO reqDTO) {
        this.tel = reqDTO.getTel();
        this.address = reqDTO.getAddress();
        this.email = reqDTO.getEmail();
        this.profileName = reqDTO.getProfileName();
        this.name = reqDTO.getName();
        this.birth = reqDTO.getBirth();
    }

    public void updateCompanyInfo(UserRequest.CompanyInfoUpdateDTO reqDTO) {
        this.tel = reqDTO.getTel();
        this.address = reqDTO.getAddress();
        this.email = reqDTO.getEmail();
        this.profileName = reqDTO.getProfileName();
    }
}
